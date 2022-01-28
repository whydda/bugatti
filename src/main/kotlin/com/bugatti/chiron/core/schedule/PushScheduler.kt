package com.bugatti.chiron.core.schedule

import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.model.SendMessageBatchRequest
import com.amazonaws.services.sqs.model.SendMessageBatchRequestEntry
import com.bugatti.chiron.core.component.AWSSqsComponent
import com.bugatti.chiron.core.model.dto.SqsPayload
import com.bugatti.chiron.core.utils.CommonUtils
import com.bugatti.chiron.core.utils.JsonUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.concurrent.Executor

@Suppress("UNREACHABLE_CODE")
@Component
class PushScheduler(
    private val amazonSQS: AmazonSQS,
    private val awsSqsComponent: AWSSqsComponent,
    private var realTimeThreadPoolTaskExecutor: Executor,
    private var topicSubscriptionThreadPoolTaskExecutor: Executor,
    private var massThreadPoolTaskExecutor: Executor,
    @Value("\${bugatti.engine.realtime.on-off}") private val realTimeOnOff: Boolean,
    @Value("\${bugatti.engine.topic-subscription.on-off}") private val topicSubscriptionOnOff: Boolean,
    @Value("\${bugatti.engine.mass.on-off}") private val massOnOff: Boolean,
    @Value("\${aws.sqs.test-queue}") private val queueUrl: String
) {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(PushScheduler::class.java)
    }

    @Async
    @Scheduled(fixedDelay = 1000)
    fun startTheEngine() {
        if (realTimeOnOff) {
            val runnable = object : Runnable {
                override fun run() {
                    val messageList = awsSqsComponent.getMessageListInQueueToString()
                    if (messageList.isEmpty()) {
                        return
                    }
                    for (message in messageList) {
                        runCatching {
                            val notification = JsonUtils.deserialize(message.body, SqsPayload::class.java)
                            LOGGER.info("#1 realtime engine message : {}", message.body)
                            awsSqsComponent.doneMessageInQueue(message)
                        }.getOrThrow()
                    }
                }
            }
            realTimeThreadPoolTaskExecutor.execute(runnable)
        }

    }

    @Async
    @Scheduled(fixedDelay = 1000)
    fun startTheEngine2() {
        if (topicSubscriptionOnOff) {
            val runnable = object : Runnable {
                override fun run() {
                    val messageList = awsSqsComponent.getMessageListInQueueToString()
                    if (messageList.isEmpty()) {
                        return
                    }
                    for (message in messageList) {
                        runCatching {
                            val notification = JsonUtils.deserialize(message.body, SqsPayload::class.java)
                            LOGGER.info("#2 topic subscription engine message : {}", message.body)
                            awsSqsComponent.doneMessageInQueue(message)
                        }.getOrThrow()
                    }
                }
            }
            topicSubscriptionThreadPoolTaskExecutor.execute(runnable)
        }
    }

    @Async
    @Scheduled(fixedDelay = 1000)
    fun startTheEngine3() {
        if (massOnOff) {
            val runnable = object : Runnable {
                override fun run() {
                    val messageList = awsSqsComponent.getMessageListInQueueToString()
                    if (messageList.isEmpty()) {
                        return
                    }
                    for (message in messageList) {
                        runCatching {
                            val notification = JsonUtils.deserialize(message.body, SqsPayload::class.java)
                            LOGGER.info("#3 mass engine message : {}", message.body)
                            awsSqsComponent.doneMessageInQueue(message)
                        }.getOrThrow()
                    }
                }
            }
            massThreadPoolTaskExecutor.execute(runnable)
        }
    }

    @Async
    @Scheduled(fixedDelay = 1000)
    fun startTheEngine4() {
        if (massOnOff) {
            val runnable = object : Runnable {
                override fun run() {
                    val messageList = awsSqsComponent.getMessageListInQueueToString()
                    if (messageList.isEmpty()) {
                        return
                    }
                    for (message in messageList) {
                        runCatching {
                            val notification = JsonUtils.deserialize(message.body, SqsPayload::class.java)
                            LOGGER.info("#3 mass engine message : {}", message.body)
                            awsSqsComponent.doneMessageInQueue(message)
                        }.getOrThrow()
                    }
                }
            }
            massThreadPoolTaskExecutor.execute(runnable)
        }
    }

    @Async
    @Scheduled(fixedDelay = 1000)
    fun startTheEngine5() {
        if (massOnOff) {
            val runnable = object : Runnable {
                override fun run() {
                    val messageList = awsSqsComponent.getMessageListInQueueToString()
                    if (messageList.isEmpty()) {
                        return
                    }
                    for (message in messageList) {
                        runCatching {
                            val notification = JsonUtils.deserialize(message.body, SqsPayload::class.java)
                            LOGGER.info("#3 mass engine message : {}", message.body)
                            awsSqsComponent.doneMessageInQueue(message)
                        }.getOrThrow()
                    }
                }
            }
            massThreadPoolTaskExecutor.execute(runnable)
        }
    }

    @Async
    @Scheduled(fixedDelay = 1000)
    fun startTheEngine6() {
        if (massOnOff) {
            val runnable = object : Runnable {
                override fun run() {
                    val messageList = awsSqsComponent.getMessageListInQueueToString()
                    if (messageList.isEmpty()) {
                        return
                    }
                    for (message in messageList) {
                        runCatching {
                            val notification = JsonUtils.deserialize(message.body, SqsPayload::class.java)
                            LOGGER.info("#3 mass engine message : {}", message.body)
                            awsSqsComponent.doneMessageInQueue(message)
                        }.getOrThrow()
                    }
                }
            }
            massThreadPoolTaskExecutor.execute(runnable)
        }
    }

    @Async
    @Scheduled(fixedDelay = 1000)
    fun putMessages() {
        var groupId = 0
        while (true) {
            ++groupId
            val messages = mutableListOf<SendMessageBatchRequestEntry>()
            for (i in 1..10) {
                val id = CommonUtils.getId()

                messages.add(
                    SendMessageBatchRequestEntry("${id}", JsonUtils.serialize(SqsPayload(id, "안녕하시오 $id")))
                        .withMessageGroupId("$groupId")
                        .withMessageDeduplicationId("${id}")
                )

                amazonSQS.sendMessageBatch(
                    SendMessageBatchRequest()
                        .withQueueUrl(queueUrl)
                        .withEntries(messages)
                )
            }
        }
    }

}


