package com.chuck.list.domain

import com.chuck.list.domain.entities.Joke
import javax.inject.Inject

class JokesListUseCase @Inject constructor(
    private val repository: JokesListRepository
) {
    suspend fun getJoke(): Joke? = repository.getJoke()
}