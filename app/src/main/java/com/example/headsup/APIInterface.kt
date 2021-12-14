package com.example.headsup

import retrofit2.Call
import retrofit2.http.*

interface APIInterface {

        @POST("/celebrities/")
        fun addData(@Body personData :Celebrity): Call<Celebrity>

        @GET("/celebrities/")
        fun getCelebrityData(): Call<List<Celebrity>>

        @PUT("/celebrities/{id}")
        fun updateCelebrityData(@Path("id")id:Int, @Body personData :Celebrity): Call<Celebrity>


        @DELETE("/celebrities/{id}")

        fun deleteCelebrityData (@Path("id")id:Int): Call<Void>

    }