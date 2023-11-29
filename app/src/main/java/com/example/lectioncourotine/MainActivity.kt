package com.example.lectioncourotine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings.Global.getString
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.lectioncourotine.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.buttonLoad.setOnClickListener {
            lifecycleScope.launch {
                loadData()
            }
        }
    }

    private suspend fun loadData() {
        Log.d("MainActivity", "Load started: $this")
        binding.progress.isVisible = true
        binding.buttonLoad.isEnabled = false
        val city = loadCity()
        binding.tvLocation.text = city
        val temp = loadTemperature(city)
        binding.tvTemperature.text = temp.toString()
        binding.progress.isVisible = false
        binding.buttonLoad.isEnabled = true
        Log.d("MainActivity", "Load finished: $this")
    }

    private suspend fun loadCity(): String {
        delay(5000)
        return "Moscow"
    }

    private suspend fun loadTemperature(city: String): Int {
        Toast.makeText(
            this,
            getString(R.string.loading_temperature_toast, city),
            Toast.LENGTH_SHORT
        ).show()
        delay(5000)
        return 17
    }

}