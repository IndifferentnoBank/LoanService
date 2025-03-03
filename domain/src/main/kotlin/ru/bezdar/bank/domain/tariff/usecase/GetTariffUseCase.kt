package ru.bezdar.bank.domain.tariff.usecase

import ru.bezdar.bank.domain.common.usecase.UseCaseWithoutParams
import ru.bezdar.bank.domain.tariff.TariffDbDataSource
import ru.bezdar.bank.domain.tariff.model.Tariff

interface GetTariffUseCase: UseCaseWithoutParams<List<Tariff>>

class GetTariffUseCaseImpl(
    private val tariffDbDataSource: TariffDbDataSource,
) : GetTariffUseCase {
    override suspend fun execute(): List<Tariff> {
        return tariffDbDataSource.getTariff()
    }
}
