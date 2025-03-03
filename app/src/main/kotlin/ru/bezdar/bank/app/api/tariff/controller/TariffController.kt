package ru.bezdar.bank.app.api.tariff.controller

import ru.bezdar.bank.app.api.tariff.model.body.CreateTariffBody
import ru.bezdar.bank.app.api.tariff.model.body.toDomain
import ru.bezdar.bank.app.api.tariff.model.response.TariffResponse
import ru.bezdar.bank.app.api.tariff.model.response.toResponse
import ru.bezdar.bank.domain.tariff.usecase.CreateTariffUseCase
import ru.bezdar.bank.domain.tariff.usecase.GetTariffUseCase

class TariffController(
    private val createTariffUseCase: CreateTariffUseCase,
    private val getTariffUseCase: GetTariffUseCase,
) {

    suspend fun getTariffs(): List<TariffResponse> {
        return getTariffUseCase().getOrThrow().map { it.toResponse() }
    }

    suspend fun createTariff(body: CreateTariffBody): TariffResponse {
        return createTariffUseCase(body.toDomain()).getOrThrow().toResponse()
    }
}
