package com.chuck.data.list.di

import com.chuck.data.list.repository.JokesListDataRepository
import com.chuck.data.list.repository.RealJokesListDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface JokesListDataRepositoryModule {

    @Binds
    fun bindsJokesListDataRepository(r: RealJokesListDataRepository): JokesListDataRepository
}