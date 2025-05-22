package com.toast1ng.webtoon.product.application.port.`in`.command

import com.toast1ng.webtoon.common.PagingCommand

data class GetPagingWebtoonEpisodesCommand(
    override val pageNo: Int,
    override val pageSize: Int,
    val webtoonId: Long,
) : PagingCommand()
