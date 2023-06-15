package com.example.clastic.data.network


import com.example.clastic.data.entity.Response
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("predict")
    fun predict(
        @Field("url") url: String,
        ): Call<Response>

}