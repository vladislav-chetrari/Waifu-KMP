package org.vchetrari.waifu.feature.list.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WaifuResponse(
    @SerialName("image_id")
    val id: Int,
    @SerialName("is_nsfw")
    val isNSFW: Boolean,
    val tags: List<TagResponse>,
    val url: String,
)