package ru.bezdar.bank.app.api.loan.model.response

import kotlinx.serialization.Serializable
import ru.bezdar.bank.app.api.common.model.IdDto
import ru.bezdar.bank.app.api.common.model.toResponse
import ru.bezdar.bank.app.api.tariff.model.response.TariffResponse
import ru.bezdar.bank.app.api.tariff.model.response.toResponse
import ru.bezdar.bank.app.common.serializers.InstantAsDateStringSerializer
import ru.bezdar.bank.app.common.serializers.UUIDAsStringSerializer
import ru.bezdar.bank.domain.loan.model.Loan
import java.time.Instant
import java.util.UUID

@Serializable
data class LoanResponse(
    val id: IdDto,
    val tariff: TariffResponse,
    @Serializable(with = UUIDAsStringSerializer::class)
    val bankAccountId: UUID,
    @Serializable(with = InstantAsDateStringSerializer::class)
    val startDate: Instant,
    @Serializable(with = InstantAsDateStringSerializer::class)
    val endDate: Instant,
    val paidSum: Double,
    val monthlyPayment: Double,
    val debt: Double,
)

fun Loan.toResponse() = LoanResponse(
    id = id.toResponse(),
    tariff = tariff.toResponse(),
    bankAccountId = bankAccountId,
    startDate = startDate,
    endDate = endDate,
    paidSum = paidSum,
    monthlyPayment = monthlyPayment,
    debt = debt,
)
