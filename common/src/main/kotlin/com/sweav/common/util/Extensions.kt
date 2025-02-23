package com.sweav.common.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import com.sweav.common.util.JsonUtils

inline fun <reified T : Any> T.getLogger(): Logger = when {
    this::class.isCompanion ->
        LoggerFactory.getLogger(this::class.java.enclosingClass)

    else ->
        LoggerFactory.getLogger(this::class.java)
}

fun <T> String.toObject(clazz: Class<T>, skipLogging: Boolean = false) =
    JsonUtils.toObject(this, clazz, skipLogging = skipLogging)
