package com.homeworkfor.domain.repository

import com.homeworkfor.data.model.NasaResponseModel
import retrofit2.Response

interface NasaRepo {

    /*TODO функция по возаращению данные на сегодняшний день*/
    suspend fun getPictureOfTheDay(): Response<NasaResponseModel>

    /*TODO функция по возаращению данных на остальные дни*/
    suspend fun getPicturesOtherDay(date: String): Response<NasaResponseModel>
}