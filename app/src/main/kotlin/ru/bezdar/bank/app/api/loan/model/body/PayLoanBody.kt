package ru.bezdar.bank.app.api.loan.model.body

import io.konform.validation.Validation
import io.konform.validation.jsonschema.minimum
import kotlinx.serialization.Serializable
import ru.bezdar.bank.app.api.common.model.IdDto
import ru.bezdar.bank.app.api.common.model.toDomain
import ru.bezdar.bank.app.common.validation.Validated
import ru.bezdar.bank.domain.common.model.User
import ru.bezdar.bank.domain.loan.model.Loan
import ru.bezdar.bank.domain.loan.model.params.PayLoanParams

@Serializable
data class PayLoanBody(
    val sum: Double,
) : Validated<PayLoanBody> {
    override val validationRule: Validation<PayLoanBody> = Validation {
        PayLoanBody::sum required {
            minimum(1)
        }
    }
}

fun PayLoanBody.toDomain(loanId: IdDto, userId: IdDto) = PayLoanParams(
    loanId = loanId.toDomain<Loan>(),
    sum = sum,
    userId = userId.toDomain<User>(),
)
