package com.homeworkfor.data.retrofit.api

import com.homeworkfor.BuildConfig
import com.homeworkfor.data.model.NasaResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {
    @GET("planetary/apod?")
    suspend fun getPictureOfTheDay(
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY
    ): Response<NasaResponseModel>

    @GET("planetary/apod?")
    suspend fun getPicturesOtherDay(
        @Query("date") date: String,
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY
    ): Response<NasaResponseModel>
}