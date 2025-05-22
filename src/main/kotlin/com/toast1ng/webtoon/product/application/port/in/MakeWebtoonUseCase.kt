package com.toast1ng.webtoon.product.application.port.`in`

import com.toast1ng.webtoon.product.application.port.`in`.command.MakeWebtoonCommand

interface MakeWebtoonUseCase {
    fun makeWebtoon(command: MakeWebtoonCommand)
}