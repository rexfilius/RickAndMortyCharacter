package com.github.rexfilius.rickandmortycharacter.api

import com.squareup.moshi.Json

data class Results(
    @Json(name = "name")
    val characterName: String,

    @Json(name = "status")
    val characterStatus: String,

    @Json(name = "species")
    val characterSpecies: String,

    @Json(name = "image")
    val characterImage: String,
)

data class CharacterResponse(val results: List<Results>)