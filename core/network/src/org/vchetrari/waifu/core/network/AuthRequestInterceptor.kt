package org.vchetrari.waifu.core.network

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header

internal class AuthRequestInterceptor(
    private val config: NetworkConfig,
) : RestRequestInterceptor {

    override suspend fun intercept(httpRequestBuilder: HttpRequestBuilder): HttpRequestBuilder {
        config.restApiKey?.let { httpRequestBuilder.header("Authorization", "Bearer $it") }
        return httpRequestBuilder
    }
}