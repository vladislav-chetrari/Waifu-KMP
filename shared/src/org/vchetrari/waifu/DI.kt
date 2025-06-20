package org.vchetrari.waifu

import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.vchetrari.waifu.config.WaifuNetworkConfig
import org.vchetrari.waifu.core.network.NetworkConfig
import org.vchetrari.waifu.core.network.coreNetworkModule
import org.vchetrari.waifu.feature.list.featureWaifuModule

val di = DI {
    bindSingleton<NetworkConfig> { WaifuNetworkConfig }
    import(coreNetworkModule)
    import(featureWaifuModule)
}
