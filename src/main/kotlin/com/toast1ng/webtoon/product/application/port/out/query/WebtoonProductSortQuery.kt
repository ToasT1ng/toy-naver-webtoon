package com.toast1ng.webtoon.product.application.port.out.query

import com.toast1ng.webtoon.common.QuerySortColumn
import com.toast1ng.webtoon.common.QuerySortOption
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
