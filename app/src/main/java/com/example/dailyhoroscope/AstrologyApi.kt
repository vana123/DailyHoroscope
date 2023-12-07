package com.example.dailyhoroscope

import retrofit2.http.GET
import retrofit2.http.Path

interface AstrologyApi {

    @GET("api/horoscope/{sunsign}/today")
    suspend fun getHoroscopeToday(@Path("sunsign") sunsign: String): HoroscopeResponse
}