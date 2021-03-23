package com.bugatti.chiron.core.service

import org.springframework.stereotype.Service

@Service
class PushService {
    fun realtime(){
        println("실시간")
    }

    fun subscription(){
        println("구독")
    }
}