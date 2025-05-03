package ru.bezdar.bank.domain.loan.usecase

import ru.bezdar.bank.domain.common.error.BankAccountNotFount
import ru.bezdar.bank.domain.common.error.InvalidDate
import ru.bezdar.bank.domain.common.manage.randomDrop
import ru.bezdar.bank.domain.common.usecase.UseCase
import ru.bezdar.bank.domain.loan.LoanDbDataSource
import ru.bezdar.bank.domain.loan.model.Loan
import ru.bezdar.bank.domain.loan.model.params.NewLoanParams
import ru.bezdar.bank.domain.loan.usecase.manage.getRequest

interface CreateLoanUseCase : UseCase<NewLoanParams, Loan>

class CreateLoanUseCaseImpl(
    private val loanDbDataSource: LoanDbDataSource,
) : CreateLoanUseCase {
    override suspend fun execute(param: NewLoanParams): Loan {
        randomDrop()
        if (param.endDate <= param.startDate) throw InvalidDate()

        val response = getRequest(param.bankAccountId, param.userId.value, param.token)

        if (response.isClosed == false) {
            return loanDbDataSource.createLoan(param)
        } else {
            throw BankAccountNotFount()
        }
    }
}
