package com.chuck.data.list.repository

import com.chuck.data.list.entities.JokeData

interface JokesListDataRepository {
    suspend fun getJoke(): JokeData?
}