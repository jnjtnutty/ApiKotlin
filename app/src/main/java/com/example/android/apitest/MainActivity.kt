package com.example.android.apitest

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.android.apitest.databinding.ActivityMainBinding
import data.SessionManager
import network.APIService


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MainActivity", "onCreate")

        sessionManager = SessionManager(this)

        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener { view: View ->
            Log.i("MainActivity", "click login!")
            MainViewModel().OnLogin(sessionManager)
        }
        binding.getMobileButton.setOnClickListener { view: View ->
            Log.i("MainActivity", "click getmobile!")
            MainViewModel().OnGetMobile(sessionManager)
        }
        binding.getPromotionButton.setOnClickListener { view: View ->
            Log.i("MainActivity", "click getpro!")
            MainViewModel().OnGetPromotion(sessionManager)
        }
    }
}