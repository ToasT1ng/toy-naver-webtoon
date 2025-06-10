package com.toast1ng.webtoon.product.application.port.`in`

import com.toast1ng.webtoon.product.application.port.`in`.command.UpdateWebtoonViewsCommand

interface UpdateWebtoonViewUseCase {
    fun updateWebtoonViews(command: UpdateWebtoonViewsCommand)
}