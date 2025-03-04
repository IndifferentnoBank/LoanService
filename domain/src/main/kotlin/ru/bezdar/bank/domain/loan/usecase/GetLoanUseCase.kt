package ru.bezdar.bank.domain.loan.usecase

import ru.bezdar.bank.domain.common.usecase.UseCaseWithoutParams
import ru.bezdar.bank.domain.loan.LoanDbDataSource
import ru.bezdar.bank.domain.loan.model.Loan

interface GetLoanUseCase : UseCaseWithoutParams<List<Loan>>

class GetLoanUseCaseImpl(
    private val loanDbDataSource: LoanDbDataSource,
) : GetLoanUseCase {
    override suspend fun execute(): List<Loan> {
        return loanDbDataSource.getLoans()
    }
}