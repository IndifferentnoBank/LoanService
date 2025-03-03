package ru.bezdar.bank.domain.tariff.usecase

import ru.bezdar.bank.domain.common.usecase.UseCase
import ru.bezdar.bank.domain.tariff.TariffDbDataSource
import ru.bezdar.bank.domain.tariff.model.Tariff
import ru.bezdar.bank.domain.tariff.model.params.UpdateTariffParams

interface UpdateTariffUseCase: UseCase<UpdateTariffParams, Tariff>

class UpdateTariffUseCaseImpl(
    private val tariffDbDataSource: TariffDbDataSource,
) : UpdateTariffUseCase {
    override suspend fun execute(param: UpdateTariffParams): Tariff {
        return tariffDbDataSource.updateTariff(param)
    }
}
