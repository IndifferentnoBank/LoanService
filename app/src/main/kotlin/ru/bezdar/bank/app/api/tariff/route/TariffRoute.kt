package ru.bezdar.bank.app.api.tariff.route

import io.ktor.resources.Resource
import kotlinx.serialization.Serializable
import ru.bezdar.bank.app.api.common.model.IdDto

sealed class TariffRoute {

    @Serializable
    @Resource("/tariffs")
    data object Tariffs : TariffRoute()

    @Serializable
    @Resource("/tariffs/{tariffId}")
    class Tariff(val tariffId: IdDto) : TariffRoute()
}
