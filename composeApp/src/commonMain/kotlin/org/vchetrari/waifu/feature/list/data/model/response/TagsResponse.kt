package org.vchetrari.waifu.feature.list.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class TagsResponse(
    val nsfw: List<TagResponse>,
    val versatile: List<TagResponse>,
)