package ru.bezdar.bank.domain.tariff

import ru.bezdar.bank.domain.common.model.Id
import ru.bezdar.bank.domain.tariff.model.Tariff
import ru.bezdar.bank.domain.tariff.model.params.NewTariffParams
import ru.bezdar.bank.domain.tariff.model.params.UpdateTariffParams

interface TariffDbDataSource {

    suspend fun checkTariffExists(name: String): Boolean

    suspend fun createTariff(params: NewTariffParams): Tariff

    suspend fun getTariff(): List<Tariff>

    suspend fun updateTariff(params: UpdateTariffParams): Tariff

    suspend fun deleteTariff(params: Id<Tariff>)
}
