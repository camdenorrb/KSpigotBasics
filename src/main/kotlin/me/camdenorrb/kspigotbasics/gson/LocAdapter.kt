package me.camdenorrb.kspigotbasics.gson

import com.google.gson.*
import me.camdenorrb.kspigotbasics.struct.server
import org.bukkit.Location
import java.lang.reflect.Type
import java.util.*


class LocAdapt : JsonSerializer<Location>, JsonDeserializer<Location> {

	override fun deserialize(element: JsonElement, type: Type?, context: JsonDeserializationContext): Location {
		element.asJsonObject.run {
			val world = server.getWorld(UUID.fromString(get("worldUUID").asString))
			return Location(world, get("x").asDouble, get("y").asDouble, get("z").asDouble, get("yaw").asFloat, get("pitch").asFloat)
		}
	}

	override fun serialize(loc: Location, type: Type?, context: JsonSerializationContext) = JsonObject().apply {
		addProperty("x", loc.x)
		addProperty("y", loc.y)
		addProperty("z", loc.z)
		addProperty("yaw", loc.yaw)
		addProperty("pitch", loc.pitch)
		addProperty("worldUUID", loc.world.uid.toString())
	}

}