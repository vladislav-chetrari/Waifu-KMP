package org.vchetrari.waifu

import androidx.compose.runtime.Composable
import org.vchetrari.waifu.core.design.WaifuTheme
import org.vchetrari.waifu.feature.list.presentation.WaifuListByTagScreen

@Composable
fun App() {
    WaifuTheme {
        WaifuListByTagScreen()
    }
}
