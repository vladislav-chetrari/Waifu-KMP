import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING

plugins {
    alias(libs.plugins.buildKonfig)
}

buildkonfig {
    packageName = "com.vchetrari.core.network"

    defaultConfigs {
        buildConfigField(
            type = STRING,
            name = "apiKey",
            value = gradleLocalProperties(rootDir).getProperty("WAIFU_API_KEY").orEmpty()
        )
    }
}