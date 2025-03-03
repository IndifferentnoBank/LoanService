package ru.bezdar.bank.data.database.tariff.entity

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import ru.bezdar.bank.domain.common.model.Id
import ru.bezdar.bank.domain.tariff.model.Tariff
import java.util.UUID

object TariffTable : UUIDTable("tariffs") {

    val name = varchar("name", 255)
    val interestRate = double("interest_rate")
}


class TariffEntity(id: EntityID<UUID>) : UUIDEntity(id) {

    var name by TariffTable.name
    var interestRate by TariffTable.interestRate

    companion object : UUIDEntityClass<TariffEntity>(TariffTable)
}

fun TariffEntity.toDomain() = Tariff(
    id = Id(id.value),
    name = name,
    interestRate = interestRate,
)
