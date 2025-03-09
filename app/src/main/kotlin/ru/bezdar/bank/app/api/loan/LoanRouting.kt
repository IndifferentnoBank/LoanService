package ru.bezdar.bank.app.api.loan

import io.ktor.server.application.call
import io.ktor.server.routing.Route
import org.koin.ktor.ext.inject
import ru.bezdar.bank.app.api.common.model.toDto
import ru.bezdar.bank.app.api.loan.controller.LoanController
import ru.bezdar.bank.app.api.loan.model.body.CreateLoanBody
import ru.bezdar.bank.app.api.loan.model.body.PayLoanBody
import ru.bezdar.bank.app.api.loan.route.LoanRoute
import ru.bezdar.bank.app.common.ApiVersion
import ru.bezdar.bank.app.common.extentions.getWithVersion
import ru.bezdar.bank.app.common.extentions.postWithVersion
import ru.bezdar.bank.app.common.extentions.receiveAndValidate
import ru.bezdar.bank.app.common.extentions.respondCreated
import ru.bezdar.bank.app.common.extentions.respondSuccess
import java.util.UUID

fun Route.configureLoanRouting() {
    val controller by inject<LoanController>()

    getWithVersion<LoanRoute.Loans>(ApiVersion.V1) {
        val loans = controller.getLoans()
        call.respondSuccess(loans)
    }

    postWithVersion<LoanRoute.Loans>(ApiVersion.V1) {
        val body = call.receiveAndValidate<CreateLoanBody>()
        val userId = call.request.queryParameters["userId"]

        val loan = controller.createLoan(body, UUID.fromString(userId).toDto())
        call.respondCreated(loan)
    }

    getWithVersion<LoanRoute.Loan>(ApiVersion.V1) { params ->
        val loan = controller.getLoanById(params.loanId)
        call.respondSuccess(loan)
    }

    postWithVersion<LoanRoute.Loan>(ApiVersion.V1) { params ->
        val body = call.receiveAndValidate<PayLoanBody>()
        val userId = call.request.queryParameters["userId"]

        val loan = controller.payLoan(params.loanId, UUID.fromString(userId).toDto(), body)
        call.respondSuccess(loan)
    }

    getWithVersion<LoanRoute.LoanUser>(ApiVersion.V1) { params ->
        val loans = controller.getLoanByUserId(params.userId)
        call.respondSuccess(loans)
    }
}
