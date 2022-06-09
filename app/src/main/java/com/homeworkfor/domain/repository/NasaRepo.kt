package com.homeworkfor.domain.repository

import com.homeworkfor.data.model.NasaResponseModel
import retrofit2.Response

interface NasaRepo {

    /*TODO функция по возаращению фото дня*/
    fun getPictureOfTheDay(): Response<NasaResponseModel>
}