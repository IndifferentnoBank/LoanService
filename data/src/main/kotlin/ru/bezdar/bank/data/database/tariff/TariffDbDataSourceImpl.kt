package ru.bezdar.bank.data.database.tariff

import org.jetbrains.exposed.sql.Database
import ru.bezdar.bank.data.database.common.DatabaseDataSourse
import ru.bezdar.bank.data.database.tariff.entity.TariffEntity
import ru.bezdar.bank.data.database.tariff.entity.TariffTable
import ru.bezdar.bank.data.database.tariff.entity.toDomain
import ru.bezdar.bank.domain.common.error.TariffNotFound
import ru.bezdar.bank.domain.common.model.Id
import ru.bezdar.bank.domain.tariff.TariffDbDataSource
import ru.bezdar.bank.domain.tariff.model.Tariff
import ru.bezdar.bank.domain.tariff.model.params.NewTariffParams
import ru.bezdar.bank.domain.tariff.model.params.UpdateTariffParams

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

    override suspend fun updateTariff(params: UpdateTariffParams): Tariff = dbQuery {
        TariffEntity.findById(params.tariffId.value)?.apply {
            this.name = params.name
            this.interestRate = params.interestRate
        }?.toDomain() ?: throw TariffNotFound()
    }

    override suspend fun deleteTariff(params: Id<Tariff>) = dbQueryWithoutResult {
        val tariff = TariffEntity.findById(params.value) ?: throw TariffNotFound()
        tariff.delete()
    }
}
