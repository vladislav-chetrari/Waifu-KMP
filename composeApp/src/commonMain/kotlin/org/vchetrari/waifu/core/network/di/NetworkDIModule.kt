package org.vchetrari.waifu.core.network.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.plugin
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.vchetrari.waifu.core.network.AuthRequestInterceptor
import org.vchetrari.waifu.core.network.KtorRequestInterceptor
import org.vchetrari.waifu.core.network.NetworkLogger

object NetworkDIModule {

    val httpClient by lazy {
        val client = HttpClient(CIO) {
            defaultRequest {
                url("https://api.waifu.im/")
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
            install(Logging) {
                logger = NetworkLogger
                level = LogLevel.ALL
            }
        }
        client.plugin(HttpSend).intercept { requestBuilder ->
            val newRequestBuilder = requestInterceptors
                .fold(requestBuilder) { request, interceptor -> interceptor.intercept(request) }
            execute(newRequestBuilder)
        }
        client
    }

    private val requestInterceptors: Set<KtorRequestInterceptor> by lazy {
        setOf(
            AuthRequestInterceptor,
        )
    }
}