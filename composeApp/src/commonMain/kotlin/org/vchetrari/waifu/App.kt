package org.vchetrari.waifu

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.vchetrari.waifu.core.design.WaifuTheme
import org.vchetrari.waifu.feature.list.presentation.WaifuListByTagScreen

@Composable
@Preview
fun App() {
    WaifuTheme {
        WaifuListByTagScreen()
    }
}