package com.example.android.apitest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import network.APIService
import network.Api

class MainViewModel {

    fun OnLogin(){
        Log.i("MainActivity", "Call onLogin()")
        APIService().userLogin()
    }
    fun OnGetMobile(){
            Log.i("MainActivity", "Call getmobile()")
            APIService().getMobile()
    }
    fun OnGetPromotion(){
            Log.i("MainActivity", "Call getpromotion()")
            APIService().getPro()
    }
}