package com.eldhopj.myapplication.utils

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class DeserializerAdapter : JsonDeserializer<Any> {

    @Throws(JsonParseException::class)
    override fun deserialize(
        jsonElement: JsonElement,
        type: Type,
        jsonDeserializationContext: JsonDeserializationContext
    ): Any {

        val content: JsonElement = jsonElement.asJsonObject

        // Deserialize it. You use a new instance of Gson to avoid infinite recursion
        // to this deserializer

        // Deserialize it. You use a new instance of Gson to avoid infinite recursion
        // to this deserializer
        return Gson().fromJson(content, type)
    }
}
