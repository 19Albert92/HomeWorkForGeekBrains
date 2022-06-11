package com.homeworkfor.view.screens.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.homeworkfor.data.repository.NasaRepoImplementation
import com.homeworkfor.domain.usecase.PictureOfTheDay

class MainViewModelFactory : ViewModelProvider.Factory{

    private val nasaRepo by lazy { NasaRepoImplementation() }

    private val pictureOfTheDay by lazy { PictureOfTheDay(nasaRepo) }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       return MainViewModel(pictureOfTheDay) as T
    }
}