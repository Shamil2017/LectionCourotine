package com.example.lectioncourotine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.lectioncourotine.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.buttonLoad.setOnClickListener {
            loadData()
        }
    }

    private fun loadData() {
        binding.progress.isVisible = true
        binding.buttonLoad.isEnabled = false
        loadCity {
            binding.tvLocation.text = it
            loadTemperature(it) {
                binding.tvTemperature.text = it.toString()
                binding.progress.isVisible = false
                binding.buttonLoad.isEnabled = true
            }
        }
    }

    private fun loadCity(callback: (String) -> Unit) {
        thread {
            Thread.sleep(5000)
            runOnUiThread {
                callback.invoke("Moscow")
            }

        }
    }

    private fun loadTemperature(city: String, callback: (Int) -> Unit) {
        thread {
            runOnUiThread{
                Toast.makeText(
                    this,
                    getString(R.string.loading_temperature_toast, city),
                    Toast.LENGTH_SHORT
                ).show()
            }
            Thread.sleep(5000)
            runOnUiThread {
                callback.invoke(17)
            }

        }
    }
}