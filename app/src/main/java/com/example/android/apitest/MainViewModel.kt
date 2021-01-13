package com.example.android.apitest

import android.util.Log
import data.SessionManager
import network.APIService

class MainViewModel {


    fun OnLogin(sessionManager: SessionManager){
        Log.i("MainActivity", "Call onLogin()")
        APIService().userLogin(sessionManager)
    }
    fun OnGetMobile(sessionManager: SessionManager){
            Log.i("MainActivity", "Call getmobile()")
            APIService().getMobile(sessionManager)
    }
    fun OnGetPromotion(sessionManager: SessionManager){
            Log.i("MainActivity", "Call getpromotion()")
            APIService().getPro(sessionManager)
    }
}