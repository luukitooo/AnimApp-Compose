package com.luukitoo.characters.domain.repository

import com.luukitoo.characters.domain.model.CharacterInfo
import com.luukitoo.core.util.ResultStatus

interface CharactersRepository {

    suspend fun getCharactersByAnime(
        animeId: Long
    ): ResultStatus<CharacterInfo>
}