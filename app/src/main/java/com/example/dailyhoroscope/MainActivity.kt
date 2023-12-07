package com.example.dailyhoroscope

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Locale

class MainActivity : AppCompatActivity() {
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
            getHoroscope(selectedSign)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getHoroscope(sunsign: String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = ApiClient.astrologyApi.getHoroscopeToday(sunsign.lowercase(Locale.ROOT))
                val horoscopeText = "Horoscope for $sunsign:\n${response.horoscope}"
                val index = horoscopeText.indexOf("(c)")
                val truncatedText = if (index != -1) horoscopeText.substring(0, index) else horoscopeText
                findViewById<TextView>(R.id.textView).text = truncatedText
            } catch (e: Exception) {
                findViewById<TextView>(R.id.textView).text = "Error: ${e.message}"
            }
        }
    }
}