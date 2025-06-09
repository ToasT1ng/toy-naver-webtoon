package com.toast1ng.webtoon.product.application.service

import com.toast1ng.webtoon.product.application.port.`in`.GetWebtoonUseCase
import com.toast1ng.webtoon.product.application.port.`in`.command.GetSortedDailyWebtoonsCommand
import com.toast1ng.webtoon.product.application.port.`in`.command.WebtoonProductSortKey
import com.toast1ng.webtoon.product.application.port.out.query.ThreeWebtoonsQuery
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonProductQuery
import com.toast1ng.webtoon.product.application.port.out.ReadWebtoonProductPort
import com.toast1ng.webtoon.product.application.port.out.query.toSortQuery
import com.toast1ng.webtoon.product.domain.DayOfWeek
import com.toast1ng.webtoon.product.domain.WebtoonProduct
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class WebtoonProductReadService(
    private val readWebtoonProductPort: ReadWebtoonProductPort
) : GetWebtoonUseCase {
    override fun getWebtoon(webtoonId: Long): WebtoonProduct {
        return requireNotNull(readWebtoonProductPort.getWebtoon(WebtoonProductQuery(id = webtoonId)))
    }

    override fun getDailyWebtoons(command: GetSortedDailyWebtoonsCommand): List<WebtoonProduct> {
        //TODO : POPULAR, UPLOADED 정렬 구현
        return when (command.sortOption.key) {
            WebtoonProductSortKey.POPULAR -> {
                readWebtoonProductPort.getWebtoons(
                    WebtoonProductQuery(day = command.day)
                )
            }
            WebtoonProductSortKey.UPLOADED -> {
                readWebtoonProductPort.getWebtoons(
                    WebtoonProductQuery(day = command.day)
                )
            }
            WebtoonProductSortKey.VIEWS, WebtoonProductSortKey.RATING -> {
                readWebtoonProductPort.getSortedWebtoons(command.toSortQuery())
            }
        }
    }

    override fun getEveryWebtoons(): List<WebtoonProduct> {
        return readWebtoonProductPort.getWebtoons(WebtoonProductQuery())
    }

    override fun getRandomThreeWebtoons(day: DayOfWeek?): List<WebtoonProduct> {
        val count = readWebtoonProductPort.getWebtoonCount(WebtoonProductQuery(day = day))
        val randomRowNumbers = (1..count).shuffled().take(3)
        return readWebtoonProductPort.getRandomWebtoons(
            ThreeWebtoonsQuery(day = day, randomRowNumbers = randomRowNumbers)
        )
    }
}