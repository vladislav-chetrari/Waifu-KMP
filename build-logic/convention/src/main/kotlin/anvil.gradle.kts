import org.gradle.kotlin.dsl.dependencies

plugins {
    alias(libs.plugins.anvil)
}

anvil {
    generateDaggerFactories.set(true)
}

dependencies {
    implementation(libs.dagger)
}