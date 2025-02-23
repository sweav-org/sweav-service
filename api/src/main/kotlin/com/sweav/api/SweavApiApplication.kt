package com.sweav.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.sweav.api", "com.sweav.core"])
class SweavApiApplication

fun main(args: Array<String>) {
    runApplication<SweavApiApplication>(*args)
}
