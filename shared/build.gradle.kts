import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.BOOLEAN

plugins {
    alias(libs.plugins.buildKonfig)
}

buildkonfig {
    packageName = "com.vchetrari.waifu"

    defaultConfigs {
        val properties = gradleLocalProperties(rootDir)
        buildConfigField(STRING, "apiKey", properties.getProperty("WAIFU_API_KEY").orEmpty())
        buildConfigField(BOOLEAN, "isDebug", properties.getProperty("IS_DEBUG"))
    }
}