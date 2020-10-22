package com.example.bugatti.core.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

/**
 * Created by whydda on 10월, 2020
 */

@Configuration
@EnableAsync
class EnginePowerSettingConfinguration(val bugatiProperties: BugattiProperties) {


    @Bean(name = ["notificationThreadPoolTaskExecutor"])
    fun threadPoolTaskExecutor(): Executor {
        val realTimeExecutor = ThreadPoolTaskExecutor()
        realTimeExecutor.corePoolSize = bugatiProperties.engine.realtime.corePoolSize
        realTimeExecutor.maxPoolSize = bugatiProperties.engine.realtime.maxPoolSize
        realTimeExecutor.setQueueCapacity(bugatiProperties.engine.realtime.queueCapacity)
        realTimeExecutor.setThreadNamePrefix("[~RealTimeEngine~]")
        realTimeExecutor.initialize()
        return realTimeExecutor
    }
}