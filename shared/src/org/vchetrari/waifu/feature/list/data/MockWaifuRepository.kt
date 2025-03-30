package org.vchetrari.waifu.feature.list.data

import org.vchetrari.waifu.feature.list.data.model.domain.Tag
import org.vchetrari.waifu.feature.list.data.model.domain.Waifu

class MockWaifuRepository : WaifuRepository {

    override suspend fun tags(): Result<List<Tag>> = Result.success(
        listOf(
            Tag(1, "maid", "maid", false),
            Tag(2, "uniform", "uniform", false),
            Tag(3, "ass", "popki", true),
            Tag(4, "milf", "mamki", true),
        )
    )

    override suspend fun search(selectedTagNames: List<String>): Result<List<Waifu>> = Result.success(emptyList())
}