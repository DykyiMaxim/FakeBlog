package com.example.data.network.di

import com.example.common.Constant
import com.example.data.network.ApiService
import com.example.data.repository.GetBlogRepositoryImpl
import com.example.domain.repository.GetBlogsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@InstallIn(SingletonComponent::class)
@Module
object DataModule{

@Provides
    fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(
                GsonConverterFactory
                    .create())
            .client(
                OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }).build())
            .build()
    }
    @Provides
    fun provideApiService(retrofit: Retrofit):ApiService{
        return retrofit.create(ApiService::class.java)

    }

    @Provides
    fun provideGetBlogsRepository(apiService: ApiService):GetBlogsRepository{
        return GetBlogRepositoryImpl(apiService = apiService)
    }


}