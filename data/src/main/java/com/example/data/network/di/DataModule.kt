package com.example.data.network.di

import android.content.Context
import com.example.common.Constant
import com.example.data.local.BlogDB
import com.example.data.local.BlogDao
import com.example.data.network.ApiService
import com.example.data.repository.GetBlogDetailsRepoImpl
import com.example.data.repository.GetBlogRepositoryImpl
import com.example.data.repository.GetPageBlogsRepo
import com.example.domain.repository.GetBlogDetailsRepo
import com.example.domain.repository.GetBlogsRepository
import com.example.domain.repository.GetPageRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    fun provideDataBase(@ApplicationContext context:Context):BlogDB{
        return BlogDB.getInstance(context)
    }

    @Provides
    fun provideDAO(blogDB: BlogDB):BlogDao{
        return blogDB.getBlogDAO()
    }

    @Provides
    fun provideGetPagerRepo(apiService: ApiService):GetPageRepo{
        return GetPageBlogsRepo(apiService)
    }

    @Provides
    fun provideGetBlogDetailsRepo(apiService: ApiService): GetBlogDetailsRepo {
        return GetBlogDetailsRepoImpl(apiService)
    }


}