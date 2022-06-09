package com.homeworkfor.data.repository

import com.homeworkfor.data.model.NasaResponseModel
import com.homeworkfor.data.retrofit.NasaRetrofitInstance
import com.homeworkfor.domain.repository.NasaRepo
import retrofit2.Response

class NasaRepoImplementation() : NasaRepo {

    /*TODO функция по возаращению фото дня*/
    override fun getPictureOfTheDay(): Response<NasaResponseModel> {
        return NasaRetrofitInstance.retrofitInstance().getPictureOfTheDay()
    }
}