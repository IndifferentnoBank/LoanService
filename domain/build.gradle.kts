plugins {
    alias(libs.plugins.kotlin.gradle.plugin)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.coroutines.core)

    implementation(libs.bundles.ktor.client)
}
