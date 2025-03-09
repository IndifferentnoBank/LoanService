package ru.bezdar.bank.domain.loan.model.body

import kotlinx.serialization.Serializable

@Serializable
data class TransactionsBody(
    val type: TransactionTypes,
    val amount: Double,
    val comment: String? = null,
)
