package org.vchetrari.waifu.feature.list.data.di

import org.vchetrari.waifu.core.network.di.NetworkDIModule
import org.vchetrari.waifu.feature.list.data.DefaultWaifuRepository
import org.vchetrari.waifu.feature.list.data.WaifuRepository

object DataDIModel {

    val waifuRepository: WaifuRepository by lazy {
        DefaultWaifuRepository(httpClient = NetworkDIModule.httpClient)
    }
}