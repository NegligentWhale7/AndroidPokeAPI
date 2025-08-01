package com.solera.pokemon

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService {
    @GET("pokemon")
    fun getCharacters(@Query("offset") offset: Int, @Query("limit") limit: Int = 10):Call<CharacterResponse>
}