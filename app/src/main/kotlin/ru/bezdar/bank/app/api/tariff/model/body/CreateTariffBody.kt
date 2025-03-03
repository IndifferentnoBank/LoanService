package ru.bezdar.bank.app.api.tariff.model.body

import io.konform.validation.Validation
import io.konform.validation.jsonschema.maxLength
import io.konform.validation.jsonschema.minimum
import kotlinx.serialization.Serializable
import ru.bezdar.bank.app.api.common.model.IdDto
import ru.bezdar.bank.app.api.common.model.toDomain
import ru.bezdar.bank.app.common.validation.Validated
import ru.bezdar.bank.app.common.validation.isNotBlankAndEmpty
import ru.bezdar.bank.domain.tariff.model.Tariff
import ru.bezdar.bank.domain.tariff.model.params.NewTariffParams
import ru.bezdar.bank.domain.tariff.model.params.UpdateTariffParams

@Serializable
data class CreateTariffBody(
    val name: String,
    val interestRate: Double,
) : Validated<CreateTariffBody> {
    override val validationRule: Validation<CreateTariffBody> = Validation {
        CreateTariffBody::name required {
            isNotBlankAndEmpty()
            maxLength(255)
        }
        CreateTariffBody::interestRate required {
            minimum(0)
        }
    }
}

fun CreateTariffBody.toDomainCreate() = NewTariffParams(
    name = name,
    interestRate = interestRate,
)

fun CreateTariffBody.toDomainUpdate(tariffId: IdDto) = UpdateTariffParams(
    tariffId = tariffId.toDomain<Tariff>(),
    name = name,
    interestRate = interestRate,
)
