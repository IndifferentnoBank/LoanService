package ru.bezdar.bank.domain.common.model

import java.util.UUID

@JvmInline
value class Id<out T>(val value: UUID)
