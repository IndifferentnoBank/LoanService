package ru.bezdar.bank.app.api.common.auth

import io.ktor.server.auth.Principal
import ru.bezdar.bank.app.api.common.model.IdDto

data class UserPrincipal(
    val userId: IdDto,
) : Principal
