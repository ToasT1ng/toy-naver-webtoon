package com.toast1ng.webtoon.common

abstract class PagingCommand{
    abstract val pageNo: Int
    abstract val pageSize: Int
}