package ru.bezdar.bank.domain.loan.model.response

import kotlinx.serialization.Serializable

@Serializable
data class GetBankAccountResponse(
    val name: String,
    val accountNumber: String,
    val balance: Double,
    val isClosed: Boolean,
)
