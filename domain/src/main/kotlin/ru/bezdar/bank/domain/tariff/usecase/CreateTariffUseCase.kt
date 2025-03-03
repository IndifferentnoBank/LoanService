package ru.bezdar.bank.domain.tariff.usecase

import ru.bezdar.bank.domain.common.error.InvalidRate
import ru.bezdar.bank.domain.common.error.TariffAlreadyExists
import ru.bezdar.bank.domain.common.usecase.UseCase
import ru.bezdar.bank.domain.tariff.TariffDbDataSource
import ru.bezdar.bank.domain.tariff.model.Tariff
import ru.bezdar.bank.domain.tariff.model.params.NewTariffParams

interface CreateTariffUseCase: UseCase<NewTariffParams, Tariff>

class CreateTariffUseCaseImpl(
    private val tariffDbDataSource: TariffDbDataSource,
) : CreateTariffUseCase {

    override suspend fun execute(param: NewTariffParams): Tariff {
        val isTariffAlreadyExists = tariffDbDataSource.checkTariffExists(param.name)
        if (isTariffAlreadyExists) throw TariffAlreadyExists()
        if (param.interestRate < 0) throw InvalidRate()

        return tariffDbDataSource.createTariff(param)
    }
}
