package org.vchetrari.waifu.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.plugin
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import co.touchlab.kermit.Logger as Kermit

val coreNetworkModule by DI.Module {
    bindSingleton<HttpClient> {
        val config = instance<NetworkConfig>()
        //apply multi-binding if needed, for now only internal interceptors are used
        val interceptors = setOf<RestRequestInterceptor>(
            AuthRequestInterceptor(config),
        )
        val client = HttpClient(CIO) {
            defaultRequest {
                url(config.restBaseUrl)
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        if (config.isLoggingEnabled) Kermit.d(message)
                    }
                }
            }
        }
        client.plugin(HttpSend).intercept { requestBuilder ->
            val newRequestBuilder = interceptors
                .fold(requestBuilder) { request, interceptor -> interceptor.intercept(request) }
            execute(newRequestBuilder)
        }
        client
    }
}