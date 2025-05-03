package ru.bezdar.bank.domain.common.manage

import ru.bezdar.bank.domain.common.error.InternalServerError
import java.time.Instant
import kotlin.time.Duration.Companion.minutes

fun randomDrop() {
    val minutes = Instant.now().epochSecond.minutes.inWholeMinutes
    val random = (0..100).random()
    if (minutes % 2 == 0L && random < 90) {
        throw InternalServerError()
    } else if (minutes % 2 == 1L && random < 50) {
        throw InternalServerError()
    }
}
