package com.bugatti.chiron.core.schedule

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

/**
 * Created by whydda on 10월, 2020
 */

@Component
class PushScheduler(
        @Value("\${bugatti.engine.realtime.on-off}")  private val realTimeOnOff: Boolean,
        @Value("\${bugatti.engine.topic-subscription.on-off}")  private val topicSubscriptionOnOff: Boolean
) {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(PushScheduler::class.java)
    }

    @Scheduled(fixedDelay = 10000)
    fun startTheEngine(){
        if(realTimeOnOff){
            LOGGER.info("realtime engine on/off {}", realTimeOnOff);
        }
        if(topicSubscriptionOnOff){
            LOGGER.info("topic subscription engine on/off {}", topicSubscriptionOnOff);
        }
    }

}


