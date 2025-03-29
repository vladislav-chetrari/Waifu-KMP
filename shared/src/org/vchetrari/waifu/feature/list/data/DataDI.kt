package org.vchetrari.waifu.feature.list.data

import org.vchetrari.waifu.core.network.NetworkDI

object DataDI {

    val waifuRepository: WaifuRepository by lazy {
        DefaultWaifuRepository(httpClient = NetworkDI.httpClient)
    }
}