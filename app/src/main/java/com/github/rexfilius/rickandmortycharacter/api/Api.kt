package com.github.rexfilius.rickandmortycharacter.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://rickandmortyapi.com/"
val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

object CharacterApi {

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ApiService::class.java)
    }
}

interface ApiService {

    @GET("api/character")
    suspend fun getCharacters(): CharacterResponse
}

class CharacterRepository(private val apiService: ApiService) {

    suspend fun getCharacters() = apiService.getCharacters()
}
