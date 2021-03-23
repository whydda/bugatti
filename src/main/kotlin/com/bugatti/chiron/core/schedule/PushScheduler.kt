package com.bugatti.chiron.core.schedule

import com.bugatti.chiron.core.config.BugattiProperties
import com.bugatti.chiron.core.service.PushService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

/**
 * Created by whydda on 10월, 2020
 */

@Component
class PushScheduler(
        val bugatiProperties: BugattiProperties,
        val pushService: PushService
) {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(this::class.java)
    }

    @Scheduled(fixedDelay = 10000)
    fun startTheEngine(){
        if(bugatiProperties.engine.realtime.onOff){
            LOGGER.info("realtime engine on/off {}", bugatiProperties.engine.realtime.onOff);
            pushService.realtime()
        }
        if(bugatiProperties.engine.topicSubscription.onOff){
            LOGGER.info("topic subscription engine on/off {}", bugatiProperties.engine.topicSubscription.onOff);
            pushService.subscription()
        }
    }

}


