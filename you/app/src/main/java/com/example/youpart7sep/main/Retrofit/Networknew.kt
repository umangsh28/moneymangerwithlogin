package com.masai.taskmanagerapp.main.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


object Network {
    private const val BASE_URL = "http://13.232.169.202:8080/"
    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
}
