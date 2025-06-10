package com.toast1ng.webtoon.product.application.port.`in`.command

import com.toast1ng.webtoon.common.domain.PagingCommand
import com.toast1ng.webtoon.common.domain.SortDirection
import com.toast1ng.webtoon.common.domain.SortKey
import com.toast1ng.webtoon.common.domain.SortOption

data class GetPagingWebtoonEpisodesCommand(
    override val pageNo: Int,
    override val pageSize: Int,
    override val sortOptions: List<SortOption<WebtoonEpisodeSortKey>> = listOf(
        SortOption(
            key = WebtoonEpisodeSortKey.UPLOADED,
            direction = SortDirection.DESC
        )
    ),
    val webtoonId: Long,
) : PagingCommand<WebtoonEpisodeSortKey>()

enum class WebtoonEpisodeSortKey : SortKey {
    UPLOADED,
}
