package ru.bezdar.bank.data.database.loan.entity

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.timestamp
import ru.bezdar.bank.data.database.tariff.entity.TariffEntity
import ru.bezdar.bank.data.database.tariff.entity.TariffTable
import ru.bezdar.bank.data.database.tariff.entity.toDomain
import ru.bezdar.bank.domain.common.model.Id
import ru.bezdar.bank.domain.loan.model.Loan
import java.util.UUID

object LoanTable : UUIDTable("loans") {

    val bankAccountId = uuid("bank_account_id")
    val startDate = timestamp("start_date")
    val endDate = timestamp("end_date")
    val paidSum = double("paid_sum")
    val monthlyPayment = double("monthly_payment")
    val debt = double("debt")

    val tariffId = reference("tariff_id", TariffTable.id)
}

class LoanEntity(id: EntityID<UUID>) : UUIDEntity(id) {

    var bankAccountId by LoanTable.bankAccountId
    var startDate by LoanTable.startDate
    var endDate by LoanTable.endDate
    var paidSum by LoanTable.paidSum
    var monthlyPayment by LoanTable.monthlyPayment
    var debt by LoanTable.debt

    var tariff by TariffEntity referencedOn LoanTable.tariffId

    companion object : UUIDEntityClass<LoanEntity>(LoanTable)
}

fun LoanEntity.toDomain() = Loan(
    id = Id(id.value),
    tariff = tariff.toDomain(),
    bankAccountId = bankAccountId,
    startDate = startDate,
    endDate = endDate,
    paidSum = paidSum,
    monthlyPayment = monthlyPayment,
    debt = debt,
)
