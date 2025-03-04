package ru.bezdar.bank.app.common.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import ru.bezdar.bank.app.common.DateFormattingUtils.formatToString
import ru.bezdar.bank.app.common.DateFormattingUtils.toInstant
import ru.bezdar.bank.app.common.DateFormattingUtils.toLocalDate
import java.time.Instant

object InstantAsDateStringSerializer : KSerializer<Instant> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("Instant", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Instant {
        val string = decoder.decodeString()
        return string.toLocalDate().toInstant()
    }

    override fun serialize(encoder: Encoder, value: Instant) {
        encoder.encodeString(value.formatToString())
    }
}
