package data

import android.content.Context
import android.content.SharedPreferences
import com.example.android.apitest.MainActivity
import com.example.android.apitest.MainViewModel
import com.example.android.apitest.R

class SessionManager(context: MainActivity)
{
        private var prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

        companion object {
            const val USER_TOKEN = "user_token"
        }

        fun saveAuthToken(token: String) {
            val editor = prefs.edit()
            editor.putString(USER_TOKEN, token)
            editor.apply()
        }

        fun fetchAuthToken(): String? {
            return prefs.getString(USER_TOKEN, null)
        }
}
