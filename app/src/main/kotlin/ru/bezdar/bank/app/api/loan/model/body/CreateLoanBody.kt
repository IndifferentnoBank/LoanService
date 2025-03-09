package ru.bezdar.bank.app.api.loan.model.body

import io.konform.validation.Validation
import io.konform.validation.jsonschema.minimum
import kotlinx.serialization.Serializable
import ru.bezdar.bank.app.api.common.model.IdDto
import ru.bezdar.bank.app.api.common.model.toDomain
import ru.bezdar.bank.app.common.serializers.InstantAsDateStringSerializer
import ru.bezdar.bank.app.common.validation.Validated
import ru.bezdar.bank.domain.common.model.User
import ru.bezdar.bank.domain.loan.model.params.NewLoanParams
import ru.bezdar.bank.domain.tariff.model.Tariff
import java.time.Instant

@Serializable
data class CreateLoanBody(
    val tariffId: IdDto,
    val bankAccountId: IdDto,
    @Serializable(with = InstantAsDateStringSerializer::class)
    val startDate: Instant,
    @Serializable(with = InstantAsDateStringSerializer::class)
    val endDate: Instant,
    val sum: Double,
) : Validated<CreateLoanBody> {
    override val validationRule: Validation<CreateLoanBody> = Validation {
        CreateLoanBody::sum required {
            minimum(1)
        }
    }
}

fun CreateLoanBody.toDomain(userId: IdDto) = NewLoanParams(
    tariffId = tariffId.toDomain<Tariff>(),
    bankAccountId = bankAccountId.value,
    startDate = startDate,
    endDate = endDate,
    sum = sum,
    userId = userId.toDomain<User>()
)
