package ru.bezdar.bank.app.common.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import ru.bezdar.bank.app.common.extentions.toUUIDOrNull
import ru.bezdar.bank.domain.common.error.InvalidUUID
import java.util.UUID

object UUIDAsStringSerializer : KSerializer<UUID> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): UUID {
        val string = decoder.decodeString()
        return string.toUUIDOrNull() ?: throw InvalidUUID()
    }

    override fun serialize(encoder: Encoder, value: UUID) {
        val string = value.toString()
        encoder.encodeString(string)
    }
}
