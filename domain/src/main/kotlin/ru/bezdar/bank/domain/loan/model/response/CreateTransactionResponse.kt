package ru.bezdar.bank.domain.loan.model.response

import ru.bezdar.bank.domain.loan.model.body.TransactionTypes
import java.time.Instant
import java.util.UUID

class CreateTransactionResponse(
    val id: UUID,
    val date: Instant,
    val amount: Double,
    val comment: String?,
    val type: TransactionTypes,
    val status: TransactionStatus,
)