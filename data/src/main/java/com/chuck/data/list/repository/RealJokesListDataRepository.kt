package com.chuck.data.list.repository

import com.chuck.data.list.entities.JokeData
import com.chuck.data.networking.NetworkInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RealJokesListDataRepository @Inject constructor(
    private val networkInterface: NetworkInterface
): JokesListDataRepository {
    override suspend fun getJoke(): JokeData? {
        return suspendCoroutine { continuation ->
            CoroutineScope(Dispatchers.Default).launch {
                networkInterface.getJoke().enqueue( object : Callback<JokeData> {
                    override fun onResponse(call: Call<JokeData>, response: Response<JokeData>) {
                        if (response.isSuccessful) {
                            val joke = response.body()
                            continuation.resume(joke)
                        } else {
                            continuation.resume(null)
                        }
                    }

                    override fun onFailure(call: Call<JokeData>, t: Throwable) {
                        continuation.resume(null)
                    }
                })
            }
        }
    }
}