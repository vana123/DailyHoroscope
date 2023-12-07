package com.example.dailyhoroscope

data class HoroscopeResponse(
    val sunsign: String,
    val credit: String,
    val date: String,
    val horoscope: String,
    val meta: Meta
)
