package ru.bezdar.bank.plugin.deployment

enum class BuildType {
    DEV, LIVE
}

fun BuildType.isDev(): Boolean = this == BuildType.DEV
