package ru.bezdar.bank.app.api.tariff.controller

import ru.bezdar.bank.app.api.common.model.IdDto
import ru.bezdar.bank.app.api.common.model.toDomain
import ru.bezdar.bank.app.api.tariff.model.body.CreateTariffBody
import ru.bezdar.bank.app.api.tariff.model.body.toDomainCreate
import ru.bezdar.bank.app.api.tariff.model.body.toDomainUpdate
import ru.bezdar.bank.app.api.tariff.model.response.TariffResponse
import ru.bezdar.bank.app.api.tariff.model.response.toResponse
import ru.bezdar.bank.domain.tariff.model.Tariff
import ru.bezdar.bank.domain.tariff.usecase.CreateTariffUseCase
import ru.bezdar.bank.domain.tariff.usecase.DeleteTariffUseCase
import ru.bezdar.bank.domain.tariff.usecase.GetTariffUseCase
import ru.bezdar.bank.domain.tariff.usecase.UpdateTariffUseCase
import kotlin.getOrThrow

class TariffController(
    private val createTariffUseCase: CreateTariffUseCase,
    private val getTariffUseCase: GetTariffUseCase,
    private val updateTariffUseCase: UpdateTariffUseCase,
    private val deleteTariffUseCase: DeleteTariffUseCase,
) {

    suspend fun getTariffs(): List<TariffResponse> {
        return getTariffUseCase().getOrThrow().map { it.toResponse() }
    }

    suspend fun createTariff(body: CreateTariffBody): TariffResponse {
        return createTariffUseCase(body.toDomainCreate()).getOrThrow().toResponse()
    }

    suspend fun updateTariff(tariffId: IdDto, body: CreateTariffBody): TariffResponse {
        return updateTariffUseCase(body.toDomainUpdate(tariffId)).getOrThrow().toResponse()
    }

    suspend fun deleteTariff(tariffId: IdDto) {
        deleteTariffUseCase(tariffId.toDomain<Tariff>()).getOrThrow()
    }
}
