package com.toast1ng.webtoon.common

interface QuerySortColumn {
    val columnName: String
}

abstract class PagingQuery<T : QuerySortColumn> {
    abstract val pageNumber: Int
    abstract val pageSize: Int
    open val sortOptions: List<QuerySortOption<T>> = emptyList()
}

data class QuerySortOption<T : QuerySortColumn>(
    val key: T,
    val direction: SortDirection
)

