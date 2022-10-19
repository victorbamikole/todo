package com.victor.interviewtask.data.network.remote

import com.victor.interviewtask.data.network.remote.NetworkConstant.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetroInstance {
    //The retro instance class for the base url is created
    companion object {
        val BaseURL =  BASE_URL

        private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttp = OkHttpClient.Builder().addInterceptor(logger).connectTimeout(30, TimeUnit.SECONDS)

        fun getRetroInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttp.build())
                .build()

        }

    }
}