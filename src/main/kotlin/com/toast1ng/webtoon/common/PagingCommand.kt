package com.toast1ng.webtoon.common

interface SortKey

abstract class PagingCommand<T : SortKey> {
    abstract val pageNo: Int
    abstract val pageSize: Int
    open val sortOptions: List<SortOption<T>> = emptyList()
}

data class SortOption<T : SortKey>(
    val key: T,
    val direction: SortDirection
)