package org.vchetrari.waifu.core.network

interface NetworkConfig {

    val restBaseUrl: String
    val restApiKey: String?
    val isLoggingEnabled: Boolean
}