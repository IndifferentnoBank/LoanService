package ru.bezdar.bank.app.api.loan.route

import io.ktor.resources.Resource
import kotlinx.serialization.Serializable
import ru.bezdar.bank.app.api.common.model.IdDto

sealed class LoanRoute {

    @Serializable
    @Resource("/loans")
    data object Loans : LoanRoute()

    @Serializable
    @Resource("/loans/{loanId}")
    class Loan(val loanId: IdDto) : LoanRoute()
}
