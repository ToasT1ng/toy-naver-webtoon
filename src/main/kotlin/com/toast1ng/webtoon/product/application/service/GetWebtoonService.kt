package com.toast1ng.webtoon.product.application.service

import com.toast1ng.webtoon.product.application.port.`in`.GetWebtoonUseCase
import com.toast1ng.webtoon.product.application.port.out.GetThreeWebtoonsQuery
import com.toast1ng.webtoon.product.application.port.out.GetWebtoonProductQuery
import com.toast1ng.webtoon.product.application.port.out.GetWebtoonsQuery
import com.toast1ng.webtoon.product.application.port.out.ReadWebtoonProductPort
import com.toast1ng.webtoon.product.domain.DayOfWeek
import com.toast1ng.webtoon.product.domain.WebtoonProduct
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class GetWebtoonService(
    private val readWebtoonProductPort: ReadWebtoonProductPort
) : GetWebtoonUseCase {
    override fun getWebtoon(webtoonId: Long): WebtoonProduct {
        return requireNotNull(readWebtoonProductPort.getWebtoon(GetWebtoonProductQuery(id = webtoonId)))
    }

    override fun getWebtoons(day: DayOfWeek): List<WebtoonProduct> {
        return readWebtoonProductPort.getWebtoons(GetWebtoonProductQuery(day = day))
    }

    override fun getRandomThreeWebtoons(day: DayOfWeek?): List<WebtoonProduct> {
        val count = readWebtoonProductPort.getWebtoonCount(GetWebtoonsQuery(day = day))
        val randomRowNumbers = (1..count).shuffled().take(3)
        return readWebtoonProductPort.getRandomThreeWebtoon(
            GetThreeWebtoonsQuery(day = day, randomRowNumbers = randomRowNumbers)
        )
    }

    override fun getEveryWebtoons(): List<WebtoonProduct> {
        return readWebtoonProductPort.getWebtoons(GetWebtoonProductQuery())
    }
}