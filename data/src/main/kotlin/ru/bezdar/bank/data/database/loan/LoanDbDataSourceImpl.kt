package ru.bezdar.bank.data.database.loan

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.and
import ru.bezdar.bank.data.database.common.DatabaseDataSourse
import ru.bezdar.bank.data.database.loan.entity.LoanEntity
import ru.bezdar.bank.data.database.loan.entity.LoanTable
import ru.bezdar.bank.data.database.loan.entity.toDomain
import ru.bezdar.bank.data.database.tariff.entity.TariffEntity
import ru.bezdar.bank.domain.common.error.LoanNotFound
import ru.bezdar.bank.domain.common.error.TariffNotFound
import ru.bezdar.bank.domain.common.model.Id
import ru.bezdar.bank.domain.loan.LoanDbDataSource
import ru.bezdar.bank.domain.loan.model.Loan
import ru.bezdar.bank.domain.loan.model.params.NewLoanParams
import ru.bezdar.bank.domain.loan.model.params.PayLoanParams
import java.time.temporal.ChronoUnit

class LoanDbDataSourceImpl(override val database: Database) : LoanDbDataSource, DatabaseDataSourse {

    override suspend fun checkLoanPaid(params: Id<Loan>): Boolean = dbQuery {
        LoanEntity.find { LoanTable.id eq params.value and (LoanTable.debt eq 0.toDouble()) }.singleOrNull() != null
    }

    override suspend fun createLoan(params: NewLoanParams): Loan = dbQuery {
        val tariffEntity = TariffEntity.findById(params.tariffId.value) ?: throw TariffNotFound()
        val daysAmount = ChronoUnit.DAYS.between(params.startDate, params.endDate).toDouble()

        val loanEntity = LoanEntity.new {
            this.tariff = tariffEntity
            this.bankAccountId = params.bankAccountId
            this.startDate = params.startDate
            this.endDate = params.endDate
            this.paidSum = 0.toDouble()
            this.monthlyPayment = params.sum/daysAmount
            this.debt = params.sum
        }
        loanEntity.toDomain()
    }

    override suspend fun getLoans(): List<Loan> = dbQuery {
        LoanEntity.all().map { it.toDomain() }
    }

    override suspend fun getLoanById(params: Id<Loan>): Loan = dbQuery {
        LoanEntity.findById(params.value)?.toDomain() ?: throw LoanNotFound()
    }

    override suspend fun payLoanById(params: PayLoanParams): Loan = dbQuery {
        if (LoanEntity.findById(params.loanId.value)!!.debt < params.sum) {
            LoanEntity.findById(params.loanId.value)?.apply {
                this.paidSum = this.paidSum + params.sum
                this.debt = 0.toDouble()
            }?.toDomain() ?: throw LoanNotFound()
        } else {
            LoanEntity.findById(params.loanId.value)?.apply {
                this.paidSum = this.paidSum + params.sum
                this.debt = this.debt - params.sum
            }?.toDomain() ?: throw LoanNotFound()
        }
    }
}
