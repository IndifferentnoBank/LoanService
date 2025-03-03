package ru.bezdar.bank.domain.tariff.usecase

import ru.bezdar.bank.domain.common.model.Id
import ru.bezdar.bank.domain.common.usecase.UseCase
import ru.bezdar.bank.domain.tariff.TariffDbDataSource
import ru.bezdar.bank.domain.tariff.model.Tariff

interface DeleteTariffUseCase : UseCase<Id<Tariff>, Unit>

class DeleteTariffUseCaseImpl(
    private val tariffDbDataSource: TariffDbDataSource,
) : DeleteTariffUseCase {
    override suspend fun execute(param: Id<Tariff>) {
        tariffDbDataSource.deleteTariff(param)
    }
}