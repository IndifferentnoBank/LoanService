package ru.bezdar.bank.app.common.extentions

import java.util.UUID

@Suppress("SwallowedException")
fun String.toUUIDOrNull(): UUID? = try {
    UUID.fromString(this)
} catch (e: IllegalArgumentException) {
    null
}

fun String.trimSlash(): String = trim(SLASH)

private const val SLASH = '/'
