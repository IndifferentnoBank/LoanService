package ru.bezdar.bank.app.api.loan.controller

import ru.bezdar.bank.app.api.common.model.IdDto
import ru.bezdar.bank.app.api.common.model.toDomain
import ru.bezdar.bank.app.api.loan.model.body.CreateLoanBody
import ru.bezdar.bank.app.api.loan.model.body.PayLoanBody
import ru.bezdar.bank.app.api.loan.model.body.toDomain
import ru.bezdar.bank.app.api.loan.model.response.LoanResponse
import ru.bezdar.bank.app.api.loan.model.response.toResponse
import ru.bezdar.bank.domain.common.model.User
import ru.bezdar.bank.domain.loan.model.Loan
import ru.bezdar.bank.domain.loan.usecase.CreateLoanUseCase
import ru.bezdar.bank.domain.loan.usecase.GetLoanByIdUseCase
import ru.bezdar.bank.domain.loan.usecase.GetLoanByUserIdUseCase
import ru.bezdar.bank.domain.loan.usecase.GetLoanUseCase
import ru.bezdar.bank.domain.loan.usecase.PayLoanUseCase

class LoanController(
    private val createLoanUseCase: CreateLoanUseCase,
    private val getLoansUseCase: GetLoanUseCase,
    private val getLoanUseCase: GetLoanByIdUseCase,
    private val payLoanUseCase: PayLoanUseCase,
    private val getLoanByUserIdUseCase: GetLoanByUserIdUseCase,
) {

    suspend fun createLoan(body: CreateLoanBody, userId: IdDto): LoanResponse {
        return createLoanUseCase(body.toDomain(userId)).getOrThrow().toResponse()
    }

    suspend fun getLoans(): List<LoanResponse> {
        return getLoansUseCase().getOrThrow().map { it.toResponse() }
    }

    suspend fun getLoanById(loanId: IdDto): LoanResponse {
        return getLoanUseCase(loanId.toDomain<Loan>()).getOrThrow().toResponse()
    }

    suspend fun payLoan(loanId: IdDto, userId: IdDto, body: PayLoanBody): LoanResponse {
        return payLoanUseCase(body.toDomain(loanId, userId)).getOrThrow().toResponse()
    }

    suspend fun getLoanByUserId(userId: IdDto): List<LoanResponse> {
        return getLoanByUserIdUseCase(userId.toDomain<User>()).getOrThrow().map { it.toResponse() }
    }
}
