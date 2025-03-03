package ru.bezdar.bank.app.common.validation

import io.konform.validation.Validation
import ru.bezdar.bank.app.common.extentions.validateAndThrow

interface Validated<R> {
    val validationRule: Validation<R>

    @Suppress("UNCHECKED_CAST")
    fun validate() {
        validationRule.validateAndThrow(this as R)
    }
}
