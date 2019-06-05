package dwolla.util

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

internal class Deserializer<T : Any> (private val gson: Gson, private val javaClassName: Class<T>) : ResponseDeserializable<T> {
    override fun deserialize(content: String) = gson.fromJson(content, javaClassName)!!
}
