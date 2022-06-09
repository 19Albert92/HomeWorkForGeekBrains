package com.homeworkfor.data.retrofit.api

import com.homeworkfor.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {
    @GET("planetary/apod?api_key=DEMO_KEY")
    fun getPictureOfTheDay(
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY
    )
}