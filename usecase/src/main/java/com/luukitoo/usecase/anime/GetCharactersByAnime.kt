package com.luukitoo.usecase.anime

import com.luukitoo.characters.domain.model.CharacterInfo
import com.luukitoo.characters.domain.repository.CharactersRepository
import com.luukitoo.core.base.usecase.UseCase
import com.luukitoo.core.util.ResultStatus
import javax.inject.Inject

class GetCharactersByAnime @Inject constructor(
    private val charactersRepository: CharactersRepository
) : UseCase<GetCharactersByAnimeParams, ResultStatus<CharacterInfo>> {

    override suspend fun execute(parameter: GetCharactersByAnimeParams): ResultStatus<CharacterInfo> {
        return charactersRepository.getCharactersByAnime(
            animeId = parameter.animeId
        )
    }
}

data class GetCharactersByAnimeParams(val animeId: Long)