package com.example.bugatti.core.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("bugatti")
data class BugattiProperties(val engine : Engine)

data class Engine(var realtime : RealTime)

data class RealTime(
        var corePoolSize : Int,
        var maxPoolSize : Int,
        var queueCapacity : Int
)
