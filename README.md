Waifu-KMP is a multimodular Kotlin and Compose Multiplatform app for Android, iOS and Windows (PC).

It uses public [Waifu-API](https://docs.waifu.im/) to fetch and display images.

## Tech Stack
- **Build-tools**: [Gradle](https://gradle.org/) (project level, with version catalog) + [Amper](https://github.com/JetBrains/amper) (module level) + [BuildKonfig](https://github.com/yshrsmz/BuildKonfig)
- **Framework**: [Kotlin Multiplatform](https://kotlinlang.org/lp/multiplatform/) + [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/)
- **Asynchronous programming**: [Kotlin Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
- **Dependency Injection**: [Kodein](https://kosi-libs.org/kodein/7.25/index.html)
- **Logging**: [Kermit](https://kermit.touchlab.co/)
- **UI & Presentation**: [Material 3](https://m3.material.io/) + [ViewModel](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-viewmodel.html) + [Navigation](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-navigation-routing.html)
- **Data**: [Ktor Client](https://ktor.io/docs/client-create-new-application.html)

## Project structure
- **[core](core) modules** - reusable, adjusted to project context, feature-agnostic components
- **[feature](feature) modules** - specific features, might expose UI (Composables) or/and Domain (UseCase-s)
- **[shared](shared)** - umbrella module, combines features in main [App](shared/src/org/vchetrari/waifu/App.kt) Composable
- **[android-app](android-app)** - wraps App Composable from **shared** for Android Application
- **[ios-app](ios-app)** - wraps App Composable from **shared** for iOS Application
- **[jvm-app](jvm-app)** - wraps App Composable from **shared** for JVM Application

