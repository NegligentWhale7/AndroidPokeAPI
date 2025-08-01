package com.solera.pokemon

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonService {
    @GET("pokemon")
    fun getCharacters(@Query("offset") offset: Int, @Query("limit") limit: Int = 10):Call<CharacterResponse>

    @GET
    fun getPokemonSprite(@Url url: String): Call<PokemonSpriteResponse>
}