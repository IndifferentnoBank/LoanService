package ru.bezdar.bank.app.common.validation

import io.konform.validation.ValidationBuilder

fun ValidationBuilder<String>.isNotBlankAndEmpty() = addConstraint(
    errorMessage = "must not consist only of spaces or be empty"
) { value -> value.isNotBlank() }

fun ValidationBuilder<*>.containsAnything(vararg props: List<*>) = addConstraint(
    errorMessage = "Must contain at least one value"
) {
    props.any { it.isNotEmpty() }
}

fun ValidationBuilder<*>.anyPresent(vararg props: Any?) = addConstraint(
    errorMessage = "Must contain at least one value"
) {
    props.any { it != null }
}
