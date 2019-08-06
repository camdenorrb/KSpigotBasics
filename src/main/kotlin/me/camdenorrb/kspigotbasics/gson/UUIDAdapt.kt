package me.camdenorrb.kspigotbasics.gson

import com.google.gson.*
import java.lang.reflect.Type
import java.util.*

class UUIDAdapt : JsonSerializer<UUID>, JsonDeserializer<UUID> {

	override fun serialize(uuid: UUID, p1: Type?, p2: JsonSerializationContext?): JsonElement {
		return JsonPrimitive(uuid.toString())
	}

	override fun deserialize(element: JsonElement, p1: Type?, p2: JsonDeserializationContext?): UUID {
		return UUID.fromString(element.asString)
	}

}