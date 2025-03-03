package ru.bezdar.bank.domain.loan.model

import ru.bezdar.bank.domain.common.model.Id
import ru.bezdar.bank.domain.tariff.model.Tariff
import java.time.Instant
import java.util.UUID

data class Loan(
    val id: Id<Loan>,
    val tariff: Tariff,
    val bankAccountId: UUID,
    val startDate: Instant,
    val endDate: Instant,
    val paidSum: Double,
    val monthlyPayment: Double,
    val debt: Double,
)
