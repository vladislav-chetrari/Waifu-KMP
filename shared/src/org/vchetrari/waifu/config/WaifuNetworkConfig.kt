package org.vchetrari.waifu.config

import com.vchetrari.waifu.BuildKonfig
import org.vchetrari.waifu.core.network.NetworkConfig

object WaifuNetworkConfig : NetworkConfig {

    override val restBaseUrl = "https://api.waifu.im/"
    override val restApiKey: String? = BuildKonfig.apiKey.takeUnless(String::isBlank)
    override val isLoggingEnabled = BuildKonfig.isDebug
}