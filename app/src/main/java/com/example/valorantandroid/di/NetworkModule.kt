package com.example.valorantandroid.di

import com.example.valorantandroid.data.ValorantApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideOkHttpInterceptor(): OkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit
        .Builder()
        .client(okHttpClient)
        .baseUrl("https://valorant-api.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideValorantApi(retrofit: Retrofit): ValorantApi = retrofit
        .create(ValorantApi::class.java)
}