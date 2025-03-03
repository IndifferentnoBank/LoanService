plugins {
    alias(libs.plugins.kotlin.gradle.plugin)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.build.config)
    alias(libs.plugins.docker.java)
    application
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.bundles.ktor.server)

    implementation(libs.reflections)

    implementation(libs.koin)
    implementation(libs.logback)

    implementation(libs.konform)

    implementation(project(":domain"))
    implementation(project(":data"))
}
