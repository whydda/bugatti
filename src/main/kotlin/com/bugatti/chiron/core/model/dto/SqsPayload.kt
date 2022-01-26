package com.bugatti.chiron.core.model.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class SqsPayload(
    val id: Long,
    val message: String
)