package com.example.battlecity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.battlecity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(binding.root) }
        
    }
}