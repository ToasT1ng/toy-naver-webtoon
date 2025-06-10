package com.toast1ng.webtoon.member.domain.error

import com.toast1ng.webtoon.common.WebtoonException
import com.toast1ng.webtoon.common.response.ResponseCode

class UserAuthException(
    override val code: ResponseCode,
) : WebtoonException(code)