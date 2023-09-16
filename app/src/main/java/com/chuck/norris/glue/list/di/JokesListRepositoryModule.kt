package com.chuck.norris.glue.list.di

import com.chuck.list.domain.JokesListRepository
import com.chuck.norris.glue.list.adapters.JokesListRepositoryAdapter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class JokesListRepositoryModule {

    @Binds
    abstract fun bindsJokesListRepository(r: JokesListRepositoryAdapter): JokesListRepository
}