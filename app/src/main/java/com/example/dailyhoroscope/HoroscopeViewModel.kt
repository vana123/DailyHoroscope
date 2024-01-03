package com.example.dailyhoroscope

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HoroscopeViewModel(private val repository: HoroscopeRepository) : ViewModel() {

    private val _horoscopeText = MutableLiveData<String>()
    val horoscopeText: LiveData<String> = _horoscopeText

    fun getHoroscope(sunsign: String) {
        viewModelScope.launch {
            try {
                val response = repository.getHoroscopeToday(sunsign)
                val horoscopeText = "Horoscope for $sunsign:\n${response.horoscope}"
                val index = horoscopeText.indexOf("(c)")
                val truncatedText = if (index != -1) horoscopeText.substring(0, index) else horoscopeText
                _horoscopeText.value = truncatedText
            } catch (e: Exception) {
                _horoscopeText.value = "Error: ${e.message}"
            }
        }
    }
}