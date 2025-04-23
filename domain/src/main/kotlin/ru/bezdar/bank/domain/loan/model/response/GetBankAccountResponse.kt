package ru.bezdar.bank.domain.loan.model.response

import java.time.Instant
import java.util.UUID

class GetBankAccountResponse(
    val id: UUID,
    val name: String,
    val accountNumber: String,
    val balance: Double,
    val isClosed: Boolean,
    val createdAt: Instant,
)
