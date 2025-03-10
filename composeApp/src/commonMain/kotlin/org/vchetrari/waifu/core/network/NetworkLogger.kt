package org.vchetrari.waifu.core.network

import io.ktor.client.plugins.logging.Logger
import co.touchlab.kermit.Logger as Kermit

object NetworkLogger : Logger {

    override fun log(message: String) = Kermit.d { message }
}