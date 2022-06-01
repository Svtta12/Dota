package science.example.dota.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import science.example.dota.data.PostsResponse


interface ApiInterface {
    @GET("heroStats")
    fun getSinglePost(): Call<List<PostsResponse>>


    companion object {
        var BASE_URL = "https://api.opendota.com/api/"
        var BASE_IMAGE = "https://api.opendota.com"
        fun create(): ApiInterface {
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}

