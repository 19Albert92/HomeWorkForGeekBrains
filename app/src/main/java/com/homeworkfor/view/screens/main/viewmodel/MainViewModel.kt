package com.homeworkfor.view.screens.main.viewmodel

import androidx.lifecycle.*
import com.homeworkfor.data.model.NasaResponseModel
import com.homeworkfor.domain.usecase.PictureOfTheDay
import com.homeworkfor.view.AppState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(
    private val pictureOfTheDay: PictureOfTheDay
) : ViewModel(), CoroutineScope by MainScope() {

    private val informationDataImage = MutableLiveData<AppState<NasaResponseModel>>()

    val requestViewModelData: LiveData<AppState<NasaResponseModel>> = informationDataImage

    fun getPicturesOtherDay(date: String) {
        launch {
            informationDataImage.value = AppState.Loading()
            val requestData = pictureOfTheDay.getPicturesOtherDay(date)
            informationDataImage.value = getResponseDate(requestData)
        }
    }

    /* -------------------------------------------------------------------------------------------- */

    fun getPictureOfTheDay() {
        launch {
            informationDataImage.value = AppState.Loading()
            val requestData = pictureOfTheDay.getPictureOfTheDay()
            informationDataImage.value = getResponseDate(requestData)
        }
    }

    private fun getResponseDate(response: Response<NasaResponseModel>): AppState<NasaResponseModel> {
        return if (response.isSuccessful) {
            response.body()!!.let {
                return AppState.Success(it)
            }
        } else {
            AppState.Error(message = "К сожалению что то пошло не так. Попробуйте позже!")
        }
    }
}