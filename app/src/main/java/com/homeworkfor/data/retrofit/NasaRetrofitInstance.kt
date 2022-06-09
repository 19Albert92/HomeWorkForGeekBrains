package com.homeworkfor.data.retrofit

import com.homeworkfor.MainConstance
import com.homeworkfor.data.retrofit.api.NasaApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NasaRetrofitInstance {

    private val interceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(MainConstance.BASE_URL_NASA)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val retrofitInstance = {
        retrofit().create(NasaApi::class.java)
    }
}