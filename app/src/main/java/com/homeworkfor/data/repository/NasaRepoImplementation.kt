package com.homeworkfor.data.repository

import com.homeworkfor.data.model.NasaResponseModel
import com.homeworkfor.data.retrofit.NasaRetrofitInstance
import com.homeworkfor.domain.repository.NasaRepo
import retrofit2.Response

class NasaRepoImplementation() : NasaRepo {

    /*TODO функция по возаращению данные на сегодняшний день*/
    override suspend fun getPictureOfTheDay(): Response<NasaResponseModel> {
        return NasaRetrofitInstance.retrofitInstance().getPictureOfTheDay()
    }

    /*TODO функция по возаращению данных на остальные дни*/
    override suspend fun getPicturesOtherDay(date: String): Response<NasaResponseModel> {
        return NasaRetrofitInstance.retrofitInstance().getPicturesOtherDay(date = date)
    }
}