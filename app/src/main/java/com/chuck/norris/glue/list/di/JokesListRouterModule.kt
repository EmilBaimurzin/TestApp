package com.chuck.norris.glue.list.di

import com.chuck.list.ui.JokesListRouter
import com.chuck.norris.glue.list.adapters.JokesListRouterAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class JokesListRouterModule {

    @Provides
    fun providesRouter(): JokesListRouter = JokesListRouterAdapter()
}