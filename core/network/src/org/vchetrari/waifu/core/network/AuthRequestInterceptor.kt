package org.vchetrari.waifu.core.network

import com.vchetrari.core.network.BuildKonfig
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header

internal object AuthRequestInterceptor : RestRequestInterceptor {

    override suspend fun intercept(httpRequestBuilder: HttpRequestBuilder): HttpRequestBuilder {
        httpRequestBuilder.header("Authorization", "Bearer ${BuildKonfig.apiKey}")
        return httpRequestBuilder
    }
}