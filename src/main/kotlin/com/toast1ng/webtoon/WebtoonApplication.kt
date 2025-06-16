package com.toast1ng.webtoon

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class WebtoonApplication

fun main(args: Array<String>) {
    runApplication<WebtoonApplication>(*args)
}
