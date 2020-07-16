package com.droidafricana.daggerhiltplayground.repository

import com.droidafricana.daggerhiltplayground.model.Blog
import com.droidafricana.daggerhiltplayground.util.DataState
import com.droidafricana.daggerhiltplayground.room.BlogDao
import com.droidafricana.daggerhiltplayground.retrofit.BlogRetrofit
import com.droidafricana.daggerhiltplayground.retrofit.NetworkMapper
import com.droidafricana.daggerhiltplayground.room.CacheMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class MainRepository
constructor(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
){
    suspend fun getBlogs(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try{
            val networkBlogs = blogRetrofit.get()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for(blog in blogs){
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = blogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        }catch (e: Exception){
            emit(DataState.Error(e))
        }
    }
}