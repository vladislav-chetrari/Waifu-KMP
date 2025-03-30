package org.vchetrari.waifu.core.network

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.kodein.di.DI
import org.kodein.di.bindSingleton

val networkModule by DI.Module {
    bindSingleton<HttpClient> {
        //apply multi-binding if needed, for now only internal interceptors are used
        val interceptors = setOf<RestRequestInterceptor>(
            AuthRequestInterceptor,
        )
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
            val newRequestBuilder = interceptors
                .fold(requestBuilder) { request, interceptor -> interceptor.intercept(request) }
            execute(newRequestBuilder)
        }
        client
    }
}