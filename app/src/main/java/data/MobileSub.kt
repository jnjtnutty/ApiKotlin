import com.google.gson.annotations.SerializedName

data class MobileSub (
	@SerializedName("mobileSub01") val mobileSub01 : sub,
	@SerializedName("mobileSub02") val mobileSub02 : sub,
	@SerializedName("mobileSub03") val mobileSub03 : sub,
	@SerializedName("mobileSub04") val mobileSub04 : sub,
	@SerializedName("mobileSub05") val mobileSub05 : sub,
	@SerializedName("mobileSub06") val mobileSub06 : sub
)
data class sub(
	@SerializedName("brand") val brand : String,
	@SerializedName("subBtn01") val subBtn01 : detail,
	@SerializedName("subBtn02") val subBtn02 : detail,
	@SerializedName("subBtn03") val subBtn03 : detail,
	@SerializedName("subBtn04") val subBtn04 : detail,
	@SerializedName("subBtn05") val subBtn05 : detail,
	@SerializedName("subBtn06") val subBtn06 : detail
)
data class detail(@SerializedName("model") val model : String,
				  @SerializedName("display") val display : String,
				  @SerializedName("imageNormal") val imageNormal : String)