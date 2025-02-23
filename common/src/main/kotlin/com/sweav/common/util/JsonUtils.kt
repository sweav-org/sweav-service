package com.sweav.common.util

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule

object JsonUtils {
    val logger = getLogger<JsonUtils>()
    val DEFAULT_OBJECT_MAPPER: ObjectMapper = ObjectMapper()
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
        .registerModule(JavaTimeModule())
        .registerKotlinModule()
    private val EMPTY_BYTE_ARRAY = byteArrayOf()

    fun toJsonBytes(obj: Any, skipLogging: Boolean = false): ByteArray {
        return try {
            DEFAULT_OBJECT_MAPPER.writeValueAsBytes(obj)
        } catch (e: Exception) {
            if (!skipLogging) {
                logger.error("Unable to serialize to json: " + e.message, obj, e)
            }
            EMPTY_BYTE_ARRAY
        }
    }

    fun <T> toObject(json: String, clazz: Class<T>, skipLogging: Boolean = false): T? {
        return toObject(DEFAULT_OBJECT_MAPPER, json, clazz, skipLogging)
    }

    fun <T> toObject(mapper: ObjectMapper, json: String, clazz: Class<T>, skipLogging: Boolean = false): T? {
        return try {
            mapper.readValue(json, clazz)
        } catch (e: Exception) {
            if (!skipLogging) {
                logger.error(e.message, json, e)
            }
            null
        }
    }

    inline fun <reified T> toObject(json: String, skipLogging: Boolean = false): T? {
        return try {
            toObjectThrowable(json)
        } catch (e: Exception) {
            if (!skipLogging) {
                logger.error(e.message, json, e)
            }
            null
        }
    }

    inline fun <reified T> toObjectThrowable(json: String): T {
        return DEFAULT_OBJECT_MAPPER.readValue(json, object : TypeReference<T>() {})
    }
}
