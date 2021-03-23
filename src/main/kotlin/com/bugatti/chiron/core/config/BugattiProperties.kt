package com.bugatti.chiron.core.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("bugatti")
data class BugattiProperties(val engine : Engine)

data class Engine(var realtime : RealTime, var topicSubscription : TopicSubscription)

data class RealTime(
        var corePoolSize : Int,
        var maxPoolSize : Int,
        var queueCapacity : Int,
        val onOff: Boolean
)

data class TopicSubscription(
        var corePoolSize : Int,
        var maxPoolSize : Int,
        var queueCapacity : Int,
        val onOff: Boolean
)
