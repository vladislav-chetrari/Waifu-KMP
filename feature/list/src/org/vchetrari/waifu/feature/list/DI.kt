package org.vchetrari.waifu.feature.list

import io.ktor.client.*
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.vchetrari.waifu.core.network.networkModule
import org.vchetrari.waifu.feature.list.data.DefaultWaifuRepository
import org.vchetrari.waifu.feature.list.data.WaifuRepository
import org.vchetrari.waifu.feature.list.presentation.WaifuListByTagViewModel

internal val featureWaifuModule by DI.Module {
    import(networkModule)
    bindSingleton<WaifuRepository> { DefaultWaifuRepository(instance<HttpClient>()) }
    bindProvider { WaifuListByTagViewModel(instance<WaifuRepository>()) }
}