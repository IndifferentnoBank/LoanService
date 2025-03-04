package ru.bezdar.bank.domain.loan

import ru.bezdar.bank.domain.common.model.Id
import ru.bezdar.bank.domain.loan.model.Loan
import ru.bezdar.bank.domain.loan.model.params.NewLoanParams
import ru.bezdar.bank.domain.loan.model.params.PayLoanParams

interface LoanDbDataSource {

    suspend fun checkLoanPaid(params: Id<Loan>): Boolean

    suspend fun createLoan(params: NewLoanParams): Loan

    suspend fun getLoanById(params: Id<Loan>): Loan
    suspend fun getLoans(): List<Loan>

    suspend fun payLoanById(params: PayLoanParams): Loan
}
