@file:Suppress("UnnecessaryAbstractClass")

package ru.bezdar.bank.domain.common.error

abstract class ApplicationError : Throwable() {

    open val code: String = this::class.java.simpleName
}

abstract class NotFoundError : ApplicationError()

class LoanNotFound(override val message: String = "Loan with such id not found") : NotFoundError()
class TariffNotFound(override val message: String? = "Tariff with such id not found") : NotFoundError()
class BankAccountNotFount(override val message: String? = "Bank account not fount") : NotFoundError()

abstract class BadRequestError : ApplicationError()

class InvalidFields(override val message: String? = null) : BadRequestError()
class InvalidUUID(override val message: String? = "Provided invalid UUID") : BadRequestError()
class InvalidRate(override val message: String? = "Provided invalid Rate") : BadRequestError()
class InvalidDate(override val message: String? = "Provided invalid Date range") : BadRequestError()

abstract class ConflictError : ApplicationError()

class TariffAlreadyExists(override val message: String? = "Tariff already exists") : ConflictError()
class LoanAlreadyPaid(override val message: String? = "Loan already paid") : ConflictError()

abstract class PaymentRequiredError : ApplicationError()

class PaymentIsNotConfirmed(override val message: String? = "Payment is not confirmed") : PaymentRequiredError()
