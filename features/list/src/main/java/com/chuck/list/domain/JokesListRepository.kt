package com.chuck.list.domain

import com.chuck.list.domain.entities.Joke

interface JokesListRepository {
    suspend fun getJoke(): Joke?
}