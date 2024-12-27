package org.vchetrari.waifu.core.secrets

import org.vchetrari.waifu.BuildConfig

actual object Secrets {

    actual val bearerToken: String = BuildConfig.bearerToken
}