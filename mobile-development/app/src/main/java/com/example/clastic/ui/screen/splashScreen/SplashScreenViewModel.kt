package com.example.clastic.ui.screen.splashScreen

import androidx.lifecycle.ViewModel
import com.example.clastic.data.Repository
import com.example.clastic.ui.screen.authentication.components.UserData

class SplashScreenViewModel(private val repository: Repository): ViewModel() {

    fun getLoggedInUser(): UserData? {
        return repository.getLoggedInUser()
    }
}