rootProject.name = "loan-service"

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

include("app")
include("domain")
include("data")
