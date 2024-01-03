package com.example.dailyhoroscope

import java.util.Locale

class HoroscopeRepository(private val astrologyApi: AstrologyApi) {

    suspend fun getHoroscopeToday(sunsign: String): HoroscopeResponse {
        return astrologyApi.getHoroscopeToday(sunsign.lowercase(Locale.ROOT))
    }
}