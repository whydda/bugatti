package com.bugatti.chiron.core.config

import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.AmazonSQSClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AWSConfig(@Value("\${aws.region}") val region: String) {

//    @Bean
//    fun configureAmazonS3(): AmazonS3 {
//        return AmazonS3ClientBuilder.standard()
//            .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
//            .withRegion(region).build()
//    }

    @Bean
    fun amazonSQS(): AmazonSQS {
        return AmazonSQSClientBuilder.standard().withRegion(region).build()
    }

//    @Bean
//    fun amazonSNS(): SnsClient {
//        return SnsClient.builder().region(Region.of(region)).build()
//    }
}