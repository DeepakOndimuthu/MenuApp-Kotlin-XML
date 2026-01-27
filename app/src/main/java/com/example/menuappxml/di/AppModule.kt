package com.example.menuappxml.di

import com.example.menuappxml.data.remote.ApiService
import com.example.menuappxml.data.repository.MenuRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRepository(api: ApiService): MenuRepository {
        return MenuRepository(api)
    }
}