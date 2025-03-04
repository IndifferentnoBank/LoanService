package ru.bezdar.bank.domain.loan.usecase

import ru.bezdar.bank.domain.common.error.LoanAlreadyPaid
import ru.bezdar.bank.domain.common.usecase.UseCase
import ru.bezdar.bank.domain.loan.LoanDbDataSource
import ru.bezdar.bank.domain.loan.model.Loan
import ru.bezdar.bank.domain.loan.model.params.PayLoanParams

interface PayLoanUseCase : UseCase<PayLoanParams, Loan>

class PayLoanUseCaseImpl(
    private val loanDbDataSource: LoanDbDataSource,
) : PayLoanUseCase {
    override suspend fun execute(param: PayLoanParams): Loan {
        val loanAlreadyPaid = loanDbDataSource.checkLoanPaid(param.loanId)
        if (loanAlreadyPaid) throw LoanAlreadyPaid()

        return loanDbDataSource.payLoanById(param)
    }
}