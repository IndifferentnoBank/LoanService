package ru.bezdar.bank.domain.loan.usecase.manage

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import ru.bezdar.bank.domain.common.client.client
import ru.bezdar.bank.domain.common.error.BankAccountNotFount
import ru.bezdar.bank.domain.common.error.PaymentIsNotConfirmed
import ru.bezdar.bank.domain.loan.model.body.TransactionsBody
import ru.bezdar.bank.domain.loan.model.response.CreateTransactionResponse
import ru.bezdar.bank.domain.loan.model.response.GetBankAccountResponse
import java.util.UUID
import kotlin.text.indexOf

suspend fun getRequest(bankAccountId: UUID, userId: UUID, token: String) : GetBankAccountResponse {
    val response = client.get("http://localhost:5086/bank_accounts/${bankAccountId}?userId=${userId}") {
        contentType(ContentType.Application.Json)
        header(HttpHeaders.Authorization, token)
    }
    return if (response.status == HttpStatusCode.OK) {
        return response.body<GetBankAccountResponse>()
    } else {
        throw BankAccountNotFount()
    }
}

suspend fun createTransaction(bankAccountId: UUID, userId: UUID, requestBody: TransactionsBody, token: String)
: CreateTransactionResponse {
    val response = client.post(
        "http://localhost:5086/bank_accounts/${bankAccountId}/transactions?userId=${userId}"
    ) {
        contentType(ContentType.Application.Json)
        setBody(requestBody)
        header(HttpHeaders.Authorization, token)
    }
    return if (response.body<String>().indexOf("message") == -1) {
        response.body<CreateTransactionResponse>()
    } else {
        throw PaymentIsNotConfirmed()
    }
}
