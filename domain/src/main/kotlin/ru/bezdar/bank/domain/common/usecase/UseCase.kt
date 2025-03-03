package ru.bezdar.bank.domain.common.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Suppress("TooGenericExceptionCaught")
interface UseCase<in P, out R> {
    suspend fun execute(param: P): R

    suspend operator fun invoke(
        param: P,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
    ): Result<R> = withContext(dispatcher) {
        try {
            Result.success(execute(param))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}
