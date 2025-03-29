import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.vchetrari.waifu.App

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}