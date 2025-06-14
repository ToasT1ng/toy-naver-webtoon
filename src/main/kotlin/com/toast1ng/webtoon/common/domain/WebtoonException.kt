package com.toast1ng.webtoon.common.domain

import com.toast1ng.webtoon.common.response.ResponseCode

abstract class WebtoonException(
    open val code: ResponseCode,
) : RuntimeException(code.message)