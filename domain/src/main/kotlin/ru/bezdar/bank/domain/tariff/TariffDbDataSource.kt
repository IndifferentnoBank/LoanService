package ru.bezdar.bank.domain.tariff

import ru.bezdar.bank.domain.tariff.model.Tariff
import ru.bezdar.bank.domain.tariff.model.params.NewTariffParams

interface TariffDbDataSource {

    suspend fun checkTariffExists(name: String): Boolean

    suspend fun createTariff(params: NewTariffParams): Tariff

    suspend fun getTariff(): List<Tariff>
}
