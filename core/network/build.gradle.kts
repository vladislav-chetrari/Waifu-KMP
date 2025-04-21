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
            name = "BEARER_TOKEN",
            value = gradleLocalProperties(rootDir).getProperty("BEARER_TOKEN").orEmpty()
        )
    }
}