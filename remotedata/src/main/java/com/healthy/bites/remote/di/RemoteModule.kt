package com.healthy.bites.remote.di

import com.healthy.bites.remote.RemoteDataSource
import com.healthy.bites.remote.RemoteDataSourceImpl
import com.healthy.bites.remote.api.HealthyBitesApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
class RemoteModule {
    @Provides
    fun provideRemoteDataSource(
        HealthyBitesApi: HealthyBitesApi
    ): RemoteDataSource = RemoteDataSourceImpl(HealthyBitesApi)

    @Provides
    @Singleton
    fun provideHealthyBitesApi(retrofit: Retrofit): HealthyBitesApi = retrofit.create(HealthyBitesApi::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson?): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val builder = GsonBuilder()
        builder.setLenient()
        builder.registerTypeAdapter(Date::class.java, JsonDeserializer<Date> { json, _, _ ->
            if (json.asJsonPrimitive.isNumber) {
                Date(json.asJsonPrimitive.asLong * 1000)
            } else {
                null
            }
        })
        return builder.create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return interceptor
    }
}