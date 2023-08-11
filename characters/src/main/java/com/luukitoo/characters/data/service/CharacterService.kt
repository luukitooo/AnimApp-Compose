package com.luukitoo.characters.data.service

import com.luukitoo.core.constant.NetworkConfig
import com.luukitoo.characters.data.model.CharacterInfoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {

    @GET(NetworkConfig.AnimeCharacters.ENDPOINT)
    suspend fun getCharactersByAnime(
        @Path(NetworkConfig.AnimeCharacters.Path.ANIME_ID) animeId: Long
    ): Response<CharacterInfoDto>
}