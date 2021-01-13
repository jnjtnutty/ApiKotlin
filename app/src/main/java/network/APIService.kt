package network

import MobileSub
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import data.LoginResponse
import data.Promotion
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
    private var _mobileSub = MutableLiveData<String>()
    val mobileSub: LiveData<String>
        get() = _mobileSub

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
    private val retrofitService : Api by lazy{
        buildRetrofit().create(Api::class.java)
    }


    fun userLogin(){
        val userInfo = Api.LoginInfo("admin@jaymart", "Jaymart@2020")
        retrofitService.getToken(userInfo).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                _token = response.body()?.token.toString()
                Log.i("MainActivity", "token : ${_token}")
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.i("MainActivity","Need to Login first!")
            }
        })
    }
    fun getMobile(){
        Log.i("MainActivity","_token = $_token")
        retrofitService.getMobileSub("Bearer $_token").enqueue(object : Callback<MobileSub> {
            override fun onResponse(call: Call<MobileSub>, response: Response<MobileSub>) {
//                Log.i("MainActivity", "Bearer $_token")
                if(response.isSuccessful){
                    val data = response.body()
                    Log.i("MainActivity", "modile data : $data")
                }
            }
            override fun onFailure(call: Call<MobileSub>, t: Throwable) {
                Log.i("MainActivity", t.message.toString())
            }
        })
    }
    fun getPro(){
        retrofitService.getPromotion("Bearer $_token").enqueue(object : Callback<Promotion> {
            override fun onResponse(call: Call<Promotion>, response: Response<Promotion>) {
                if(response.isSuccessful){
                    val topic = response.body()
                    Log.i("MainActivity", "modile data : $topic")
                }
            }
            override fun onFailure(call: Call<Promotion>, t: Throwable) {
                Log.i("MainActivity", t.message.toString())
            }
        })
    }
}