package com.homeworkfor.domain.usecase

import com.homeworkfor.data.model.NasaResponseModel
import com.homeworkfor.domain.repository.NasaRepo
import retrofit2.Response

class PictureOfTheDay {
    fun getPictureOfTheDay(nasaRepo: NasaRepo): Response<NasaResponseModel> {
        return nasaRepo.getPictureOfTheDay()
    }
}