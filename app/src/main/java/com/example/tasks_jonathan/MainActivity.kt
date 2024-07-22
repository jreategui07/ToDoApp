package com.example.tasks_jonathan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tasks_jonathan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
    }
}