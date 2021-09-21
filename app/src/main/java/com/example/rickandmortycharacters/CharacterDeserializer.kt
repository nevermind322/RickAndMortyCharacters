package com.example.rickandmortycharacters

import com.example.rickandmortycharacters.models.CharacterInfo
import  com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

@Deprecated("Now it is useless")
class CharacterDeserializer : JsonDeserializer<CharacterInfo?> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CharacterInfo? {

        val jsonObject: JsonObject? = json?.asJsonObject

        /*if (jsonObject != null) {
            return CharacterInfo(
                jsonObject.get("name").asString,
                jsonObject.get("status").asString,
                jsonObject.get("species").asString,
                jsonObject.get("type").asString,
                jsonObject.get("gender").asString,
                jsonObject.get("image").asString
            )
        }*/

        return null
    }
}