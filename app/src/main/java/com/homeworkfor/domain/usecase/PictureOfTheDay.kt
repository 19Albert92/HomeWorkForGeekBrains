package com.homeworkfor.domain.usecase

import com.homeworkfor.data.model.NasaResponseModel
import com.homeworkfor.domain.repository.NasaRepo
import retrofit2.Response

class PictureOfTheDay(private val nasaRepo: NasaRepo) {

    suspend fun getPictureOfTheDay(): Response<NasaResponseModel> {
        return nasaRepo.getPictureOfTheDay()
    }

    suspend fun getPicturesOtherDay(date: String): Response<NasaResponseModel> {
        return nasaRepo.getPicturesOtherDay(date)
    }
}