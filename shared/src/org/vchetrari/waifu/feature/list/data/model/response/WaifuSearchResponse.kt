package org.vchetrari.waifu.feature.list.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WaifuSearchResponse(
    @SerialName("images")
    val list: List<WaifuResponse>
)