package com.toast1ng.webtoon.product.application.port.out.query

import com.toast1ng.webtoon.common.domain.QuerySortColumn
import com.toast1ng.webtoon.common.domain.QuerySortOption
import com.toast1ng.webtoon.product.application.port.`in`.command.GetSortedDailyWebtoonsCommand
import com.toast1ng.webtoon.product.application.port.`in`.command.WebtoonProductSortKey
import com.toast1ng.webtoon.product.domain.DayOfWeek

data class WebtoonProductSortQuery(
    val id: Long? = null,
    val day: DayOfWeek? = null,
    val sortOptions: List<QuerySortOption<WebtoonProductSortColumn>> = emptyList()
)

enum class WebtoonProductSortColumn(override val columnName: String) : QuerySortColumn {
    RATING("rating"),
    VIEWS("views"),
    ;
}

fun GetSortedDailyWebtoonsCommand.toSortQuery(): WebtoonProductSortQuery {
    return WebtoonProductSortQuery(
        day = this.day,
        sortOptions = listOf(
            QuerySortOption(
                this.sortOption.key.toSortColumn(),
                this.sortOption.direction
            )
        )
    )
}

private fun WebtoonProductSortKey.toSortColumn(): WebtoonProductSortColumn {
    return when (this) {
        WebtoonProductSortKey.RATING -> WebtoonProductSortColumn.RATING
        WebtoonProductSortKey.VIEWS -> WebtoonProductSortColumn.VIEWS
        else -> throw IllegalArgumentException("WebtoonProductSortKey $this is not supported.")
    }
}