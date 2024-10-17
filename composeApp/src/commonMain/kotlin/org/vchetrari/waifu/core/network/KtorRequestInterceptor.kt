package org.vchetrari.waifu.core.network

import io.ktor.client.request.HttpRequestBuilder

interface KtorRequestInterceptor {

    suspend fun intercept(httpRequestBuilder: HttpRequestBuilder): HttpRequestBuilder
}