plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.kotlin.gradle)
    implementation(libs.ksp.gradle)
    implementation(libs.anvil.gradle)

    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}