package com.chuck.norris.glue.list.adapters

import com.chuck.data.list.repository.JokesListDataRepository
import com.chuck.list.domain.JokesListRepository
import com.chuck.list.domain.entities.Joke
import kotlinx.coroutines.delay
import javax.inject.Inject

class JokesListRepositoryAdapter @Inject constructor(
    private val dataRepository: JokesListDataRepository
): JokesListRepository {
    override suspend fun getJoke(): Joke? {
        val joke = dataRepository.getJoke()
        return if (joke != null) {
            Joke(
                joke.icon_url,
                joke.value
            )
        } else null
    }
}