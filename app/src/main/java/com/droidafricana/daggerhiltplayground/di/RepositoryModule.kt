package com.droidafricana.daggerhiltplayground.di

import com.droidafricana.daggerhiltplayground.repository.MainRepository
import com.droidafricana.daggerhiltplayground.retrofit.BlogRetrofit
import com.droidafricana.daggerhiltplayground.retrofit.NetworkMapper
import com.droidafricana.daggerhiltplayground.room.BlogDao
import com.droidafricana.daggerhiltplayground.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        retrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository{
        return MainRepository(blogDao, retrofit, cacheMapper, networkMapper)
    }
}














