package ru.bezdar.bank.domain.loan.usecase

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import ru.bezdar.bank.domain.common.client.client
import ru.bezdar.bank.domain.common.error.BankAccountNotFount
import ru.bezdar.bank.domain.common.error.InvalidDate
import ru.bezdar.bank.domain.common.usecase.UseCase
import ru.bezdar.bank.domain.loan.LoanDbDataSource
import ru.bezdar.bank.domain.loan.model.Loan
import ru.bezdar.bank.domain.loan.model.params.NewLoanParams
import ru.bezdar.bank.domain.loan.model.response.GetBankAccountResponse

interface CreateLoanUseCase : UseCase<NewLoanParams, Loan>

class CreateLoanUseCaseImpl(
    private val loanDbDataSource: LoanDbDataSource,
) : CreateLoanUseCase {
    override suspend fun execute(param: NewLoanParams): Loan {
        if (param.endDate <= param.startDate) throw InvalidDate()

        val response: String = client.get("http://51.250.33.133:8081/bank_accounts/{${param.bankAccountId}}?userId=${param.userId.value}") {
            contentType(ContentType.Application.Json)
        }.body()

        if (response.indexOf("isClosed") != -1) {
            if (response.indexOf("false") != -1) {
                return loanDbDataSource.createLoan(param)
            } else {
                throw BankAccountNotFount()
            }
        } else {
            throw BankAccountNotFount()
        }
    }
}
