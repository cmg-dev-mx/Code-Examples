package mx.dev.shellcore.android.cache.web.model


import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    @SerializedName("count")
    val count: Int = 0,
    @SerializedName("next")
    val next: String = "",
    @SerializedName("previous")
    val previous: Any? = null,
    @SerializedName("results")
    val results: List<Result> = listOf()
)