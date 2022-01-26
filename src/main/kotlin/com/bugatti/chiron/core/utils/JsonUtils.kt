package com.bugatti.chiron.core.utils

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

object JsonUtils {

    private val objectMapper = jacksonObjectMapper()
        .registerModule(JavaTimeModule())
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    fun <T> serialize(value: T) = objectMapper.writeValueAsString(value)

    fun <T> deserialize(jsonString: String, clazz: Class<T>) = objectMapper.readValue(jsonString, clazz)

}