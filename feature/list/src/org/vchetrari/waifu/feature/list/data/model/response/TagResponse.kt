package org.vchetrari.waifu.feature.list.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TagResponse(
    @SerialName("tag_id")
    val id: Int,
    val name: String,
    val description: String,
    @SerialName("is_nsfw")
    val isNSFW: Boolean,
)