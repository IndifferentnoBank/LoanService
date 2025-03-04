package ru.bezdar.bank.domain.loan.model.params

import ru.bezdar.bank.domain.common.model.Id
import ru.bezdar.bank.domain.loan.model.Loan

data class PayLoanParams(
    val loanId: Id<Loan>,
    val sum: Double,
)
