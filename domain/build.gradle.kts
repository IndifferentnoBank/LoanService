plugins {
    alias(libs.plugins.kotlin.gradle.plugin)
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.coroutines.core)
}
