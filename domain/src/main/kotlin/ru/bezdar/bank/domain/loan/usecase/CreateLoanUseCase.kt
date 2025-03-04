package ru.bezdar.bank.domain.loan.usecase

import ru.bezdar.bank.domain.common.error.InvalidDate
import ru.bezdar.bank.domain.common.usecase.UseCase
import ru.bezdar.bank.domain.loan.LoanDbDataSource
import ru.bezdar.bank.domain.loan.model.Loan
import ru.bezdar.bank.domain.loan.model.params.NewLoanParams

interface CreateLoanUseCase : UseCase<NewLoanParams, Loan>

class CreateLoanUseCaseImpl(
    private val loanDbDataSource: LoanDbDataSource,
) : CreateLoanUseCase {
    override suspend fun execute(param: NewLoanParams): Loan {
        if (param.endDate <= param.startDate) throw InvalidDate()

        return loanDbDataSource.createLoan(param)
    }
}
