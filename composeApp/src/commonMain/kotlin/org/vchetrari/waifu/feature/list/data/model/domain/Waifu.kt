package org.vchetrari.waifu.feature.list.data.model.domain

data class Waifu(
    val id: Int,
    val url: String,
    val isNSFW: Boolean,
    val tags: List<Tag>,
)