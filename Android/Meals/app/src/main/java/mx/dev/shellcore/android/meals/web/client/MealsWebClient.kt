package mx.dev.shellcore.android.meals.web.client

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MealsWebClient {

    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    val client: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}