package ru.bezdar.bank.app.common.extentions

import io.konform.validation.Invalid
import io.konform.validation.Validation
import ru.bezdar.bank.domain.common.error.InvalidFields
import kotlin.collections.joinToString
import kotlin.text.drop
import kotlin.text.trim

fun <T> Validation<T>.validateAndThrow(value: T) {
    val result = this.validate(value)
    if (result is Invalid<T>) {
        val errors = result.errors.joinToString { error ->
            "${error.dataPath.drop(1)} ${error.message}".trim()
        }
        throw InvalidFields(errors)
    }
}
