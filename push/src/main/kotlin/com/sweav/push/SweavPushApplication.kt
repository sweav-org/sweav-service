package com.sweav.push

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SweavPushApplication

fun main(args: Array<String>) {
    runApplication<SweavPushApplication>(*args)
}
