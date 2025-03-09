package ru.bezdar.bank.domain.loan.usecase

import ru.bezdar.bank.domain.common.model.Id
import ru.bezdar.bank.domain.common.model.User
import ru.bezdar.bank.domain.common.usecase.UseCase
import ru.bezdar.bank.domain.loan.LoanDbDataSource
import ru.bezdar.bank.domain.loan.model.Loan

interface GetLoanByUserIdUseCase : UseCase<Id<User>, List<Loan>>

class GetLoanByUserIdUseCaseImpl(
    private val loanDbDataSource: LoanDbDataSource,
) : GetLoanByUserIdUseCase {
    override suspend fun execute(param: Id<User>): List<Loan> {
        return loanDbDataSource.getLoanByUserId(param)
    }
}
