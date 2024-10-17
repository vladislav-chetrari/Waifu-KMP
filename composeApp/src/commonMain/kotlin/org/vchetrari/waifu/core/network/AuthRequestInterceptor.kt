package org.vchetrari.waifu.core.network

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import org.vchetrari.waifu.core.secrets.Secrets

object AuthRequestInterceptor : KtorRequestInterceptor {

    override suspend fun intercept(httpRequestBuilder: HttpRequestBuilder): HttpRequestBuilder {
        httpRequestBuilder.header("Authorization", "Bearer ${Secrets.bearerToken}")
        return httpRequestBuilder
    }
}