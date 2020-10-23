package com.bugatti.chiron

import com.bugatti.chiron.core.config.BugattiProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(BugattiProperties::class)
class BugattiApplication

fun main(args: Array<String>) {
    runApplication<BugattiApplication>(*args)
}
