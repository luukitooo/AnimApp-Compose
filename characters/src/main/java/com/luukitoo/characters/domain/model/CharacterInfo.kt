package com.luukitoo.characters.domain.model

data class CharacterInfo(
    val data: List<CharacterData>? = null,
) {
    data class CharacterData(
        val character: Character? = null,
        val role: String? = null,
    ) {
        data class Character(
            val id: Long? = null,
            val url: String? = null,
            val images: Images? = null,
            val name: String? = null,
        ) {
            data class Images(
                val jpg: JPG? = null,
            ) {
                data class JPG(
                    val imageUrl: String? = null,
                )
            }
        }
    }
}