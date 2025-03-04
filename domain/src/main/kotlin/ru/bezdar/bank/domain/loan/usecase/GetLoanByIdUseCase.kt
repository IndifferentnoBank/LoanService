package ru.bezdar.bank.domain.loan.usecase

import ru.bezdar.bank.domain.common.model.Id
import ru.bezdar.bank.domain.common.usecase.UseCase
import ru.bezdar.bank.domain.loan.LoanDbDataSource
import ru.bezdar.bank.domain.loan.model.Loan

interface GetLoanByIdUseCase : UseCase<Id<Loan>, Loan>

class GetLoanByIdUseCaseImpl(
    private val loanDbDataSource: LoanDbDataSource,
) : GetLoanByIdUseCase {
    override suspend fun execute(param: Id<Loan>): Loan {
        return loanDbDataSource.getLoanById(param)
    }
}