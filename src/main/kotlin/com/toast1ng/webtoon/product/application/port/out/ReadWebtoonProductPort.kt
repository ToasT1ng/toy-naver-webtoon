package com.toast1ng.webtoon.product.application.port.out

import com.toast1ng.webtoon.product.domain.WebtoonProduct

interface ReadWebtoonProductPort {
    fun getWebtoons(query: GetWebtoonsQuery): List<WebtoonProduct>
    fun getWebtoon(query: GetWebtoonQuery): WebtoonProduct?
    fun getWebtoonCount(query: GetWebtoonsQuery): Long
    fun getRandomThreeWebtoon(query: GetThreeWebtoonsQuery):List<WebtoonProduct>
}