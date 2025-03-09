package ru.bezdar.bank.domain.loan.model.params

import ru.bezdar.bank.domain.common.model.Id
import ru.bezdar.bank.domain.common.model.User
import ru.bezdar.bank.domain.tariff.model.Tariff
import java.time.Instant
import java.util.UUID

data class NewLoanParams(
    val tariffId: Id<Tariff>,
    val bankAccountId: UUID,
    val startDate: Instant,
    val endDate: Instant,
    val sum: Double,
    val userId: Id<User>,
)
