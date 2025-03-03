package ru.bezdar.bank.domain.common.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Suppress("TooGenericExceptionCaught")
interface UseCaseWithoutParams<out R> {
    suspend fun execute(): R

    suspend operator fun invoke(
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Result<R> = withContext(dispatcher) {
        try {
            Result.success(execute())
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}
