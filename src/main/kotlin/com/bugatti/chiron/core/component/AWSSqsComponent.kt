package com.bugatti.chiron.core.component

import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.model.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class AWSSqsComponent(
    private val amazonSQS: AmazonSQS,
    @Value("\${aws.sqs.test-queue}") val testQueueUrl: String,
    @Value("\${aws.sqs.test-dead-letter-queue}") val testDeadLetterQueueUrl: String
) {
    fun getMessageListInQueueToString(): List<Message> {
        val request = ReceiveMessageRequest(testQueueUrl)
        request.maxNumberOfMessages = 10
        request.waitTimeSeconds = 20

        return amazonSQS.receiveMessage(request).messages ?: listOf()
    }

    suspend fun doneMessageInQueue(message: Message) {
        val request = DeleteMessageRequest(testQueueUrl, message.receiptHandle)
        amazonSQS.deleteMessage(request)
    }

    suspend fun setMessageInQueue(message: String) {
        amazonSQS.sendMessage(SendMessageRequest(testQueueUrl, message))
    }

    suspend fun setMesagesBatchInQueue(messages: List<SendMessageBatchRequestEntry>) {
        amazonSQS.sendMessageBatch(SendMessageBatchRequest(testQueueUrl, messages))
    }

    fun getMessageListInDeadLetterQueueToString(): List<Message> {
        val request = ReceiveMessageRequest(testDeadLetterQueueUrl)
        request.maxNumberOfMessages = 10
        request.waitTimeSeconds = 20

        return amazonSQS.receiveMessage(request).messages ?: listOf()
    }

    suspend fun doneMessageInDeadLetterQueue(message: Message) {
        val request = DeleteMessageRequest(testDeadLetterQueueUrl, message.receiptHandle)
        amazonSQS.deleteMessage(request)
    }

    suspend fun setMessageInDeadLetterQueue(message: String) {
        amazonSQS.sendMessage(SendMessageRequest(testDeadLetterQueueUrl, message))
    }

    suspend fun setMesageInDeadLetterQueue(message: String) {
        SendMessageRequest(testDeadLetterQueueUrl, message)
    }
}