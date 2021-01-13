package network

import MobileSub
import android.content.Context
import android.util.Log
import com.example.android.apitest.MainActivity
import data.LoginResponse
import data.Promotion
import data.SessionManager
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class APIService {
    private var _token = ""

    private val BASE_URL = "https://fibo.jaymart.org/api/"

    fun getUnsafeOkHttpClient(): OkHttpClient {
        val x509TrustManager = object: X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            }

            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        }

        val trustManagers = arrayOf<TrustManager>(x509TrustManager)

        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, trustManagers, null)

        val builder = OkHttpClient.Builder()
        builder.sslSocketFactory(sslContext.socketFactory, x509TrustManager)
        builder.hostnameVerifier { _, _ -> true }

        return builder.build()
    }
    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getUnsafeOkHttpClient())
            .build()
    }
    val retrofitService : Api by lazy{
        buildRetrofit().create(Api::class.java)
    }

    fun userLogin(sessionManager: SessionManager){
        val userInfo = Api.LoginInfo("admin@jaymart", "Jaymart@2020")
        retrofitService.getToken(userInfo).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val loginResponse = response.body()
                sessionManager.saveAuthToken(loginResponse!!.token)
                Log.i("MainActivity", "token : $loginResponse")
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.i("MainActivity", "Need to Login first!")
            }
        })
    }
    fun getMobile(sessionManager: SessionManager){
        Log.i("MainActivity", "_token = ${sessionManager.fetchAuthToken()}")
        retrofitService.getMobileSub("Bearer ${sessionManager.fetchAuthToken()}").enqueue(object :
            Callback<MobileSub> {
            override fun onResponse(call: Call<MobileSub>, response: Response<MobileSub>) {
//                Log.i("MainActivity", "Bearer $_token")
                if (response.isSuccessful) {
                    val data = response.body()
                    Log.i("MainActivity", "modile data : $data")
                }
            }

            override fun onFailure(call: Call<MobileSub>, t: Throwable) {
                Log.i("MainActivity", t.message.toString())
            }
        })
    }
    fun getPro(sessionManager: SessionManager){
        retrofitService.getPromotion("Bearer ${sessionManager.fetchAuthToken()}").enqueue(object :
            Callback<Promotion> {
            override fun onResponse(call: Call<Promotion>, response: Response<Promotion>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    Log.i("MainActivity", "modile data : $data")
                }
            }

            override fun onFailure(call: Call<Promotion>, t: Throwable) {
                Log.i("MainActivity", t.message.toString())
            }
        })
    }
}