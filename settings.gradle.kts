pluginManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
        maven("https://www.jetbrains.com/intellij-repository/releases")
        maven("https://packages.jetbrains.team/maven/p/amper/amper")
        maven("https://packages.jetbrains.team/maven/p/ij/intellij-dependencies")
    }
}

plugins {
    id("org.jetbrains.amper.settings.plugin").version("0.6.0").apply(true)
}

include(
    ":core:design",
    ":core:network",
    ":feature:list",
    ":shared",
    ":android-app",
//TODO fix build of iOS app,
//     error: Module has to have at least one ios device fragment
//     ":ios-app",
    ":jvm-app",
)