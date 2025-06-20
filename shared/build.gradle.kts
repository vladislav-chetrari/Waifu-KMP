import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING

plugins {
    alias(libs.plugins.buildKonfig)
}

buildkonfig {
    packageName = "com.vchetrari.waifu"

    defaultConfigs {
        buildConfigField(STRING, "apiKey", gradleLocalProperties(rootDir).getProperty("WAIFU_API_KEY").orEmpty())
    }
}