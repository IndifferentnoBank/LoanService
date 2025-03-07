import ru.bezdar.bank.plugin.deployment.DeploymentConfig
import ru.bezdar.bank.plugin.deployment.IMAGE_BASE
import ru.bezdar.bank.plugin.deployment.IMAGE_PORT
import ru.bezdar.bank.plugin.deployment.isDev

plugins {
    alias(libs.plugins.kotlin.gradle.plugin)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.build.config)
    alias(libs.plugins.docker.java)
    application
}

buildConfig {
    buildConfigField(type = "boolean", name = "IS_DEV", value = DeploymentConfig.buildType.isDev().toString())
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = DeploymentConfig.buildType.isDev()
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

docker {
    javaApplication {
        baseImage.set(IMAGE_BASE)
        ports.set(listOf(IMAGE_PORT))
        images.set(listOf("${DeploymentConfig.imageName}:${DeploymentConfig.imageTag}"))
    }
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
