package com.toast1ng.webtoon.product.application.port.out

import com.toast1ng.webtoon.product.application.port.out.query.ThreeWebtoonsQuery
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonProductQuery
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonProductSortQuery
import com.toast1ng.webtoon.product.domain.WebtoonProduct

interface ReadWebtoonProductPort {
    fun getWebtoons(query: WebtoonProductQuery): List<WebtoonProduct>
    fun getWebtoonsByLatestEpisodeUpload(query: WebtoonProductSortQuery): List<WebtoonProduct>
    fun getSortedWebtoons(query: WebtoonProductSortQuery): List<WebtoonProduct>
    fun getWebtoon(query: WebtoonProductQuery): WebtoonProduct?
    fun getWebtoonCount(query: WebtoonProductQuery): Long
    fun getRandomWebtoons(query: ThreeWebtoonsQuery):List<WebtoonProduct>
}