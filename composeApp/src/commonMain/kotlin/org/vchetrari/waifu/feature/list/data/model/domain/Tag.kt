package org.vchetrari.waifu.feature.list.data.model.domain

data class Tag(
    val id: Int,
    val name: String,
    val description: String,
    val isNSFW: Boolean,
)