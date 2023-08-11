package com.luukitoo.characters.data.repository

import com.luukitoo.characters.data.model.toCharacterInfo
import com.luukitoo.characters.data.service.CharacterService
import com.luukitoo.characters.domain.model.CharacterInfo
import com.luukitoo.characters.domain.repository.CharactersRepository
import com.luukitoo.core.util.NetworkCaller
import com.luukitoo.core.util.ResultStatus
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val characterService: CharacterService,
) : CharactersRepository {

    override suspend fun getCharactersByAnime(animeId: Long): ResultStatus<CharacterInfo> {
        return NetworkCaller.safeCall {
            characterService.getCharactersByAnime(animeId)
        }.mapData { characterInfoDto ->
            characterInfoDto.toCharacterInfo()
        }
    }
}