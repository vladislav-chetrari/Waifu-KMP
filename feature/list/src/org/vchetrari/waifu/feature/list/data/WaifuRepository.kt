package org.vchetrari.waifu.feature.list.data

import org.vchetrari.waifu.feature.list.data.model.domain.Tag
import org.vchetrari.waifu.feature.list.data.model.domain.Waifu

interface WaifuRepository {

    suspend fun tags(): Result<List<Tag>>

    suspend fun search(selectedTagNames: List<String> = emptyList()): Result<List<Waifu>>
}