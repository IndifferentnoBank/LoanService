package ru.bezdar.bank.app.api.tariff

import io.ktor.server.application.call
import io.ktor.server.routing.Route
import org.koin.ktor.ext.inject
import ru.bezdar.bank.app.api.tariff.controller.TariffController
import ru.bezdar.bank.app.api.tariff.model.body.CreateTariffBody
import ru.bezdar.bank.app.api.tariff.route.TariffRoute
import ru.bezdar.bank.app.common.ApiVersion
import ru.bezdar.bank.app.common.extentions.deleteWithVersion
import ru.bezdar.bank.app.common.extentions.getWithVersion
import ru.bezdar.bank.app.common.extentions.postWithVersion
import ru.bezdar.bank.app.common.extentions.putWithVersion
import ru.bezdar.bank.app.common.extentions.receiveAndValidate
import ru.bezdar.bank.app.common.extentions.respondNoContent
import ru.bezdar.bank.app.common.extentions.respondSuccess

fun Route.configureTariffRouting() {
    val controller by inject<TariffController>()

    getWithVersion<TariffRoute.Tariffs>(ApiVersion.V1) {
        val tariffs = controller.getTariffs()
        call.respondSuccess(tariffs)
    }

    postWithVersion<TariffRoute.Tariffs>(ApiVersion.V1) {
        val body = call.receiveAndValidate<CreateTariffBody>()

        val tariff = controller.createTariff(body)
        call.respondSuccess(tariff)
    }

    putWithVersion<TariffRoute.Tariff>(ApiVersion.V1) { params ->
        val tariffId = params.tariffId
        val body = call.receiveAndValidate<CreateTariffBody>()

        val tariff = controller.updateTariff(tariffId, body)
        call.respondSuccess(tariff)
    }

    deleteWithVersion<TariffRoute.Tariff>(ApiVersion.V1) { params ->
        val tariffId = params.tariffId

        controller.deleteTariff(tariffId)
        call.respondNoContent()
    }
}
