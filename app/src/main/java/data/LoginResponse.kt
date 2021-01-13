package data

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status_code") var statusCode: Int,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("token") val token: String
)
