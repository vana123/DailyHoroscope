package com.example.dailyhoroscope

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: HoroscopeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val zodiacSigns = resources.getStringArray(R.array.zodiac_signs)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, zodiacSigns)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinner: Spinner = findViewById(R.id.spinner)
        spinner.adapter = adapter

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val selectedSign = spinner.selectedItem as String
            viewModel.getHoroscope(selectedSign)
        }

        viewModel = ViewModelProvider(this, HoroscopeViewModelFactory()).get(HoroscopeViewModel::class.java)

        val textView: TextView = findViewById(R.id.textView)
        viewModel.horoscopeText.observe(this, { horoscopeText ->
            textView.text = horoscopeText
        })
    }
    class HoroscopeViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val repository = HoroscopeRepository(ApiClient.astrologyApi)
            return HoroscopeViewModel(repository) as T
        }
    }
    @SuppressLint("SetTextI18n")
    private fun getHoroscope(sunsign: String) {
        viewModel.getHoroscope(sunsign)
    }
}