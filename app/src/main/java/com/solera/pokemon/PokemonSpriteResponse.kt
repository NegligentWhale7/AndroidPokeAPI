package com.solera.pokemon

import com.google.gson.annotations.SerializedName

data class PokemonSpriteResponse(
    val sprites: PokemonSprite
)

data class PokemonSprite(
    val front_default: String?
)
