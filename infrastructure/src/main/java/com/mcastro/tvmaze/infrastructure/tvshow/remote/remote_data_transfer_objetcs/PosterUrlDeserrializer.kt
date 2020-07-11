package com.mcastro.tvmaze.infrastructure.tvshow.remote.remote_data_transfer_objetcs

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class PosterUrlDeserializer : JsonDeserializer<String?> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): String {
        return json.asJsonObject["medium"].asString
    }
}