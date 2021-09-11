package com.example.rickandmortycharacters

import com.google.gson.*
import java.lang.reflect.Type

class PageDeserializer :
    JsonDeserializer<CharacterPage?> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CharacterPage? {

        val jsonObject: JsonObject? = json?.asJsonObject
        if (jsonObject != null) {

            val res = mutableListOf<CharacterInfo>()
            val charArray = jsonObject.get("results").asJsonArray
            val infoJsonObject = jsonObject.get("info").asJsonObject

            charArray.forEach {
                if (context != null) res.add(context.deserialize(it, CharacterInfo::class.java))
            }

            val infoCount = infoJsonObject.get("count").asInt
            val infoPages = infoJsonObject.get("pages").asInt

            val infoNext: String? =
                when (infoJsonObject.get("next")){
                    is JsonNull -> null
                    else -> infoJsonObject.get("next").asString
                }

            return CharacterPage(CharacterPage.Info(infoCount, infoPages, infoNext ), res)

        }
        return null
    }
}