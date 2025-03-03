@file:Suppress("ERROR_SUPPRESSION")

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.gradle.plugin)
    alias(libs.plugins.dependencyCheck)
}

subprojects {
    group = "ru.bezdar"
    version = "0.0.1"

    repositories {
        mavenCentral()
    }
}
