package org.vchetrari.waifu.core.network

import io.ktor.client.request.HttpRequestBuilder

internal interface RestRequestInterceptor {

    suspend fun intercept(httpRequestBuilder: HttpRequestBuilder): HttpRequestBuilder
}