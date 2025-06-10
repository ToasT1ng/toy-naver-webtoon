package com.toast1ng.webtoon.product.application.port.out.query

import com.toast1ng.webtoon.common.domain.PagingQuery
import com.toast1ng.webtoon.common.domain.QuerySortColumn
import com.toast1ng.webtoon.common.domain.QuerySortOption
import com.toast1ng.webtoon.product.application.port.`in`.command.GetPagingWebtoonEpisodesCommand
import com.toast1ng.webtoon.product.application.port.`in`.command.WebtoonEpisodeSortKey
import java.time.LocalDateTime

data class WebtoonEpisodePagingQuery(
    val id: Long? = null,
    val webtoonId: Long? = null,
    val uploadDateTo: LocalDateTime? = null,
    override val pageNumber: Int,
    override val pageSize: Int,
    override val sortOptions: List<QuerySortOption<WebtoonEpisodeSortColumn>>
) : PagingQuery<WebtoonEpisodeSortColumn>()

enum class WebtoonEpisodeSortColumn(override val columnName: String) : QuerySortColumn {
    UPLOAD_DATE("uploadDate"),
    ;
}

fun GetPagingWebtoonEpisodesCommand.toPagingQuery(uploadDateTo: LocalDateTime): WebtoonEpisodePagingQuery {
    return WebtoonEpisodePagingQuery(
        webtoonId = this.webtoonId,
        uploadDateTo = uploadDateTo,
        pageNumber = this.pageNo,
        pageSize = this.pageSize,
        sortOptions = this.sortOptions.map {
            QuerySortOption(
                it.key.toSortColumn(),
                it.direction
            )
        }
    )
}

private fun WebtoonEpisodeSortKey.toSortColumn(): WebtoonEpisodeSortColumn {
    return when (this) {
        WebtoonEpisodeSortKey.UPLOADED -> WebtoonEpisodeSortColumn.UPLOAD_DATE
    }
}