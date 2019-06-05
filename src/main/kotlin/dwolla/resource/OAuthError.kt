package dwolla.resource

import com.google.gson.annotations.SerializedName
import java.util.Optional

data class OAuthError(
    @JvmField val error: String,

    @JvmField @SerializedName("error_description")
    val errorDescription: Optional<String>,

    @JvmField @SerializedName("error_uri")
    val errorUri: Optional<String>
)
