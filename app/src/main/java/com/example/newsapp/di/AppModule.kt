package com.example.newsapp.di

import android.app.Application
import androidx.room.Room
import com.example.newsapp.data.local.ArticleDao
import com.example.newsapp.data.local.NewsDataBase
import com.example.newsapp.data.remote.Api
import com.example.newsapp.data.remote.interceptors.NetworkConnectionInterceptor
import com.example.newsapp.data.repository.NewsRepositoryImpl
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(networkConnectionInterceptor: NetworkConnectionInterceptor): Api {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(networkConnectionInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(Api::class.java)
    }
    @Provides
    @Singleton
    fun provideInternetInterceptor(application: Application): NetworkConnectionInterceptor {
        return NetworkConnectionInterceptor(application)
    }
    @Singleton
    @Provides
    fun provideDatabase(app: Application): NewsDataBase {
        return Room.databaseBuilder(app, NewsDataBase::class.java, "news_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDishesDao(database: NewsDataBase): ArticleDao {
        return database.getArticleDao()
    }

    @Singleton
    @Provides
    fun provideNewsRepository(api: Api, articleDao: ArticleDao): NewsRepository {
        return NewsRepositoryImpl(api, articleDao)
    }

}