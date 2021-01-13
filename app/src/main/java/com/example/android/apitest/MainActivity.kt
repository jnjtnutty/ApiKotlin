package com.example.android.apitest

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android.apitest.databinding.ActivityMainBinding
import network.APIService


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

//    override fun onSaveInstanceState(savedInstanceState: Bundle) {
//        Log.i("MainActivity","save")
//        savedInstanceState.putString("token", APIService()._token);
//        super.onSaveInstanceState(savedInstanceState)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MainActivity","onCreate")

//        if (savedInstanceState != null) {
//            Log.i("MainActivity","get")
//            APIService()._token = savedInstanceState.getString("token").toString()
//            Toast.makeText(this, APIService()._token, Toast.LENGTH_LONG).show()
//        }

        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener { view: View ->
            Log.i("MainActivity", "click login!")
            MainViewModel().OnLogin()
        }
        binding.getMobileButton.setOnClickListener { view: View ->
            Log.i("MainActivity", "click getmobile!")
            MainViewModel().OnGetMobile()
        }
        binding.getPromotionButton.setOnClickListener { view: View ->
            Log.i("MainActivity", "click getpro!")
            MainViewModel().OnGetPromotion()
        }

    }

}