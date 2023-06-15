package com.example.clastic.data.network

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val authorized = original.newBuilder()
            .addHeader("Authorization", "Basic Y2xhc3RpY19kZXY6Q2xhc3RpY0RldkJhbmdraXQ=")
            .build()

        return chain.proceed(authorized)
    }

}