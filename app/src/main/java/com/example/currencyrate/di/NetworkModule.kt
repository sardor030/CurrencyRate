package com.example.currencyrate.di

import com.example.currencyrate.BuildConfig
import com.example.currencyrate.api.network.ApiService
import com.example.currencyrate.api.network.helper.ApiHelper
import com.example.currencyrate.api.network.helper.ApiHelperImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

//    @Singleton
//    @Provides
//    fun provideOkHttpClient() = if (BuildConfig.DEBUG){
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//        OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor)
//            .build()
//    }else{
//        OkHttpClient
//            .Builder()
//            .build()
//    }
//
//    @Singleton
//    @Provides
//    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL:String): Retrofit = Retrofit.Builder()
//        .addConverterFactory(GsonConverterFactory.create())
//        .baseUrl(BASE_URL)
//        .client(okHttpClient)
//        .build()


    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideApiHelper(apiHelper: ApiHelperImp): ApiHelper = apiHelper
}