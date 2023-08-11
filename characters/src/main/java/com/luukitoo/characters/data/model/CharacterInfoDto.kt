package com.luukitoo.characters.data.model

import com.google.gson.annotations.SerializedName
import com.luukitoo.characters.domain.model.CharacterInfo

data class CharacterInfoDto(
    val data: List<CharacterDataDto>? = null,
) {
    data class CharacterDataDto(
        val character: CharacterDto? = null,
        val role: String? = null,
    ) {
        data class CharacterDto(
            @SerializedName("mal_id")
            val id: Long? = null,
            val url: String? = null,
            val images: ImagesDto? = null,
            val name: String? = null,
        ) {
            data class ImagesDto(
                val jpg: JPGDto? = null,
            ) {
                data class JPGDto(
                    @SerializedName("image_url")
                    val imageUrl: String? = null,
                )
            }
        }
    }
}

fun CharacterInfoDto.CharacterDataDto.CharacterDto.ImagesDto.JPGDto.toJPG() = CharacterInfo.CharacterData.Character.Images.JPG(
    imageUrl = imageUrl
)

fun CharacterInfoDto.CharacterDataDto.CharacterDto.ImagesDto.toImages() = CharacterInfo.CharacterData.Character.Images(
    jpg = jpg?.toJPG()
)

fun CharacterInfoDto.CharacterDataDto.CharacterDto.toCharacter() = CharacterInfo.CharacterData.Character(
    id = id,
    url = url,
    images = images?.toImages(),
    name = name
)

fun CharacterInfoDto.CharacterDataDto.toCharacterData() = CharacterInfo.CharacterData(
    character = character?.toCharacter(),
    role = role
)

fun CharacterInfoDto.toCharacterInfo() = CharacterInfo(
    data = data?.map(CharacterInfoDto.CharacterDataDto::toCharacterData)
)