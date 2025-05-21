package com.toast1ng.webtoon.product.application.port.`in`

import com.toast1ng.webtoon.common.PagingCommand

data class GetPagingWebtoonEpisodesCommand(
    override val pageNo: Int,
    override val pageSize: Int,
    val webtoonId: Long,
) : PagingCommand()
