package com.example.clastic.di

import android.content.Context
import com.example.clastic.data.Repository
import com.example.clastic.data.network.Dao

object Injection {
    fun provideRepository(context: Context): Repository {
        val dao = Dao()
        return Repository.getInstance(dao)
    }
}