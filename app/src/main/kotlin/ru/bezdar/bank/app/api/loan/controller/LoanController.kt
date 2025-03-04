package ru.bezdar.bank.app.api.loan.controller

import ru.bezdar.bank.app.api.common.model.IdDto
import ru.bezdar.bank.app.api.common.model.toDomain
import ru.bezdar.bank.app.api.loan.model.body.CreateLoanBody
import ru.bezdar.bank.app.api.loan.model.body.PayLoanBody
import ru.bezdar.bank.app.api.loan.model.body.toDomain
import ru.bezdar.bank.app.api.loan.model.response.LoanResponse
import ru.bezdar.bank.app.api.loan.model.response.toResponse
import ru.bezdar.bank.domain.loan.model.Loan
import ru.bezdar.bank.domain.loan.usecase.CreateLoanUseCase
import ru.bezdar.bank.domain.loan.usecase.GetLoanByIdUseCase
import ru.bezdar.bank.domain.loan.usecase.GetLoanUseCase
import ru.bezdar.bank.domain.loan.usecase.PayLoanUseCase

class LoanController(
    private val createLoanUseCase: CreateLoanUseCase,
    private val getLoansUseCase: GetLoanUseCase,
    private val getLoanUseCase: GetLoanByIdUseCase,
    private val payLoanUseCase: PayLoanUseCase,
) {

    suspend fun createLoan(body: CreateLoanBody): LoanResponse {
        return createLoanUseCase(body.toDomain()).getOrThrow().toResponse()
    }

    suspend fun getLoans(): List<LoanResponse> {
        return getLoansUseCase().getOrThrow().map { it.toResponse() }
    }

    suspend fun getLoanById(loanId: IdDto): LoanResponse {
        return getLoanUseCase(loanId.toDomain<Loan>()).getOrThrow().toResponse()
    }

    suspend fun payLoan(loanId: IdDto, body: PayLoanBody): LoanResponse {
        return payLoanUseCase(body.toDomain(loanId)).getOrThrow().toResponse()
    }
}
