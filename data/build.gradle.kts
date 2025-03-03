plugins {
    alias(libs.plugins.kotlin.gradle.plugin)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    api(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)
    implementation(libs.exposed.java.time)

    api(libs.hikari)
    implementation(libs.flyway.core)
    implementation(libs.postgree.connector)

    implementation(libs.bundles.ktor.client)

    implementation(project(":domain"))
}
