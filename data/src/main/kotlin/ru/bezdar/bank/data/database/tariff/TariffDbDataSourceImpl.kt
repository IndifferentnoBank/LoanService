package ru.bezdar.bank.data.database.tariff

import org.jetbrains.exposed.sql.Database
import ru.bezdar.bank.data.database.common.DatabaseDataSourse
import ru.bezdar.bank.data.database.tariff.entity.TariffEntity
import ru.bezdar.bank.data.database.tariff.entity.TariffTable
import ru.bezdar.bank.data.database.tariff.entity.toDomain
import ru.bezdar.bank.domain.tariff.TariffDbDataSource
import ru.bezdar.bank.domain.tariff.model.Tariff
import ru.bezdar.bank.domain.tariff.model.params.NewTariffParams

class TariffDbDataSourceImpl(override val database: Database) : TariffDbDataSource, DatabaseDataSourse {

    override suspend fun checkTariffExists(name: String): Boolean = dbQuery {
        TariffEntity.find { TariffTable.name eq name }.singleOrNull() != null
    }

    override suspend fun createTariff(params: NewTariffParams): Tariff = dbQuery {
        val tariffEntity = TariffEntity.new {
            this.name = params.name
            this.interestRate = params.interestRate
        }

        tariffEntity.toDomain()
    }

    override suspend fun getTariff(): List<Tariff> = dbQuery {
        TariffEntity.all().map { it.toDomain() }
    }


}
