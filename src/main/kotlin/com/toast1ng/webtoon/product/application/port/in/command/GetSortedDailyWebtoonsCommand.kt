package com.toast1ng.webtoon.product.application.port.`in`.command

import com.toast1ng.webtoon.common.domain.SortDirection
import com.toast1ng.webtoon.common.domain.SortKey
import com.toast1ng.webtoon.common.domain.SortOption
import com.toast1ng.webtoon.product.domain.DayOfWeek

data class GetSortedDailyWebtoonsCommand(
    val day: DayOfWeek,
    val sortOption: SortOption<WebtoonProductSortKey> = SortOption(
        key = WebtoonProductSortKey.POPULAR,
        direction = SortDirection.DESC
    )
)

enum class WebtoonProductSortKey : SortKey {
    POPULAR, UPLOADED, VIEWS, RATING;
}
