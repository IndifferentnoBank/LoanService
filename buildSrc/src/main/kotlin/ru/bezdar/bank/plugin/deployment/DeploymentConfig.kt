package ru.bezdar.bank.plugin.deployment

object DeploymentConfig {
    private const val KEY_RELEASE = "RELEASE"
    private const val KEY_IMAGE_NAME = "CI_REGISTRY_IMAGE"
    private const val KEY_IMAGE_TAG = "IMAGE_TAG"

    private const val DEFAULT_IMAGE_NAME = "loan-service"
    private const val DEFAULT_IMAGE_TAG = "dev"

    private val isRelease: Boolean get() = System.getenv(KEY_RELEASE).toBoolean()

    val buildType: BuildType
        get() = if (isRelease) BuildType.LIVE else BuildType.DEV

    val imageName: String
        get() = System.getenv(KEY_IMAGE_NAME).orEmpty().ifBlank { DEFAULT_IMAGE_NAME }

    val imageTag: String
        get() = System.getenv(KEY_IMAGE_TAG).orEmpty().ifBlank { DEFAULT_IMAGE_TAG }
}
