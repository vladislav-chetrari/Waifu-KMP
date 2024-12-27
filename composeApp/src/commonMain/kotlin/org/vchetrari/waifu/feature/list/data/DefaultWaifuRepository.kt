package org.vchetrari.waifu.feature.list.data

import co.touchlab.kermit.Logger
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.vchetrari.waifu.feature.list.data.model.domain.Tag
import org.vchetrari.waifu.feature.list.data.model.domain.Waifu
import org.vchetrari.waifu.feature.list.data.model.response.TagResponse
import org.vchetrari.waifu.feature.list.data.model.response.TagsResponse
import org.vchetrari.waifu.feature.list.data.model.response.WaifuResponse
import org.vchetrari.waifu.feature.list.data.model.response.WaifuSearchResponse

class DefaultWaifuRepository(
    private val httpClient: HttpClient,
) : WaifuRepository {

    override suspend fun tags(): Result<List<Tag>> = runCatching {
        httpClient.get("tags") {
            parameter("full", true)
        }.body<TagsResponse>()
    }.map { response ->
        (response.versatile + response.nsfw)
            .map { it.asDomainModel() }
    }.onFailure { exception ->
        Logger.e(exception) { "Tags request failure" }
    }

    override suspend fun search(selectedTagNames: List<String>) = runCatching {
        httpClient.get("search") {
            url {
                parameters.append("gif", "false")
                parameters.append("limit", "10")
                parameters.append("byte_size", "<1000000")
                if (selectedTagNames.isNotEmpty())
                    parameters.appendAll("included_tags", selectedTagNames)
            }
        }.body<WaifuSearchResponse>()
    }.map { response ->
        response.list
            .map { it.asDomainModel() }
    }.onFailure { exception ->
        Logger.e(exception) { "Search request failure" }
    }


    private fun TagResponse.asDomainModel(): Tag = Tag(
        id = id,
        name = name,
        description = description,
        isNSFW = isNSFW,
    )

    private fun WaifuResponse.asDomainModel(): Waifu = Waifu(
        id = id,
        url = url,
        isNSFW = isNSFW,
        tags = tags.map { it.asDomainModel() },
    )
}