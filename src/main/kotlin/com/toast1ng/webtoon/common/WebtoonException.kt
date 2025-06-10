package com.toast1ng.webtoon.common

import com.toast1ng.webtoon.common.response.ResponseCode

abstract class WebtoonException(
    open val code: ResponseCode,
) : RuntimeException(code.message)