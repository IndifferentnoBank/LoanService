@file:Suppress("UnnecessaryAbstractClass")

package ru.bezdar.bank.domain.common.error

abstract class ApplicationError : Throwable() {

    open val code: String = this::class.java.simpleName
}

abstract class NotFoundError : ApplicationError()

class LoanNotFound(override val message: String = "Loan with such id not found") : NotFoundError()
class TariffNotFound(override val message: String? = "Tariff with such id not found") : NotFoundError()

abstract class BadRequestError : ApplicationError()

class InvalidFields(override val message: String? = null) : BadRequestError()
class InvalidUUID(override val message: String? = "Provided invalid UUID") : BadRequestError()
class InvalidRate(override val message: String? = "Provided invalid Rate") : BadRequestError()

abstract class ConflictError : ApplicationError()

class TariffAlreadyExists(override val message: String? = "Tariff already exists") : ConflictError()

abstract class InternalServerError : ApplicationError()
