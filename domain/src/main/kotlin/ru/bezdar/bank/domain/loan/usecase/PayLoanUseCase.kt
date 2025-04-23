package ru.bezdar.bank.domain.loan.usecase

import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import ru.bezdar.bank.domain.common.client.client
import ru.bezdar.bank.domain.common.error.LoanAlreadyPaid
import ru.bezdar.bank.domain.common.error.PaymentIsNotConfirmed
import ru.bezdar.bank.domain.common.usecase.UseCase
import ru.bezdar.bank.domain.loan.LoanDbDataSource
import ru.bezdar.bank.domain.loan.model.Loan
import ru.bezdar.bank.domain.loan.model.body.TransactionTypes
import ru.bezdar.bank.domain.loan.model.body.TransactionsBody
import ru.bezdar.bank.domain.loan.model.params.PayLoanParams
import ru.bezdar.bank.domain.loan.model.response.CreateTransactionResponse
import ru.bezdar.bank.domain.loan.usecase.manage.createTransaction

interface PayLoanUseCase : UseCase<PayLoanParams, Loan>

class PayLoanUseCaseImpl(
    private val loanDbDataSource: LoanDbDataSource,
) : PayLoanUseCase {
    override suspend fun execute(param: PayLoanParams): Loan {
        val loanAlreadyPaid = loanDbDataSource.checkLoanPaid(param.loanId)
        if (loanAlreadyPaid) throw LoanAlreadyPaid()

        val loan = loanDbDataSource.getLoanById(param.loanId)
        val requestBody = TransactionsBody(TransactionTypes.PAY_LOAN, loan.monthlyPayment)

        val response : CreateTransactionResponse = createTransaction(
            loan.bankAccountId,
            param.userId.value,
            requestBody
        )
        return loanDbDataSource.payLoanById(param)
    }
}
