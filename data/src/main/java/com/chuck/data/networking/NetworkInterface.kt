package com.chuck.data.networking

import com.chuck.data.list.entities.JokeData
import retrofit2.Call
import retrofit2.http.GET

interface NetworkInterface {
    @GET("/jokes/random")
    fun getJoke(): Call<JokeData>
}