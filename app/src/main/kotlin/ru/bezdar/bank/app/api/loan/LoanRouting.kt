package ru.bezdar.bank.app.api.loan

import io.ktor.server.routing.Route
import org.koin.ktor.ext.inject
import ru.bezdar.bank.app.api.loan.controller.LoanController

fun Route.configureLoanRouting() {
    val controller by inject<LoanController>()

}
