package ru.bezdar.bank.domain.loan.usecase.manage

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import ru.bezdar.bank.domain.common.client.client
import ru.bezdar.bank.domain.common.error.PaymentIsNotConfirmed
import ru.bezdar.bank.domain.loan.model.body.TransactionsBody
import ru.bezdar.bank.domain.loan.model.response.CreateTransactionResponse
import ru.bezdar.bank.domain.loan.model.response.GetBankAccountResponse
import java.util.UUID
import kotlin.text.indexOf

suspend fun getRequest(bankAccountId: UUID, userId: UUID) : GetBankAccountResponse {
    return client.get("http://51.250.33.133:8081/bank_accounts/{${bankAccountId}}?userId=${userId}") {
        contentType(ContentType.Application.Json)
    }.body<GetBankAccountResponse>()
}

suspend fun createTransaction(bankAccountId: UUID, userId: UUID, requestBody: TransactionsBody)
: CreateTransactionResponse {
    val response = client.post(
        "http://51.250.33.133:8081/bank_accounts/{${bankAccountId}}/transactions?userId=${userId}"
    ) {
        contentType(ContentType.Application.Json)
        setBody(requestBody)
    }
    return if (response.body<String>().indexOf("message") == -1) {
        response.body<CreateTransactionResponse>()
    } else {
        throw PaymentIsNotConfirmed()
    }
}