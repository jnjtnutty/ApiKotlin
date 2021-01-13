package data

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("displayName") val displayName: String,
    @SerializedName("token") val token: String
)
