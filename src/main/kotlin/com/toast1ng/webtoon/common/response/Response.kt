package com.toast1ng.webtoon.common.response

import com.fasterxml.jackson.annotation.JsonInclude

interface Response<T>{
    val code: Int
    val message: String
    val data: T?
}

@JsonInclude(JsonInclude.Include.NON_NULL)
data class SuccessResponse<T>(
    override val code: Int,
    override val message: String,
    override val data: T? = null
): Response<T>

data class ErrorResponse(
    override val code: Int,
    override val message: String,
    override val data: Any? = null
) : Response<Any>