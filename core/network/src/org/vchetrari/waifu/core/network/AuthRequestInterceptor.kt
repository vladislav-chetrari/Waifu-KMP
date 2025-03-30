package org.vchetrari.waifu.core.network

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header

internal object AuthRequestInterceptor : RestRequestInterceptor {

    override suspend fun intercept(httpRequestBuilder: HttpRequestBuilder): HttpRequestBuilder {
        httpRequestBuilder.header("Authorization", "Bearer ${Secrets.BEARER_TOKEN}")
        return httpRequestBuilder
    }
}