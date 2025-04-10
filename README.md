This is a Kotlin Multiplatform project targeting Android, iOS and Windows.
A proof-of-concept that painless KMP multi-modular clean architecture project is possible.
Used Waifu API as data-source.

### Tech-specs
- Build-tools: Gradle 8.11.1 (project level, version catalog) + Amper 0.6 (module level)
- Structure:
  - **core modules** - reusable, feature-agnostic components
  - **feature modules** - specific features, might expose UI (Composables) or Domain (UseCase classes)
  - **shared** - umbrella module, combines features in main App Composable
  - **android-app** - wraps App Composable from **shared** for Android Application
  - **ios-app** - wraps App Composable from **shared** for iOS Application
  - **jvm-app** - wraps App Composable from **shared** for JVM Application
- Networking: Ktor client
- DI: Kodein

