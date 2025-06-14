package com.toast1ng.webtoon.product.adapter.out.persistence

import com.toast1ng.webtoon.common.domain.PersistenceAdapter
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.CreatorDto
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.WebtoonWithPersonDto
import com.toast1ng.webtoon.product.application.port.out.*
import com.toast1ng.webtoon.product.application.port.out.query.ThreeWebtoonsQuery
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonProductQuery
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonProductSortQuery
import com.toast1ng.webtoon.product.domain.WebtoonProduct
import java.math.BigDecimal
import java.sql.Timestamp

@PersistenceAdapter
class WebtoonProductPersistenceAdapter(
    private val mapper: WebtoonProductMapper,
    private val webtoonProductRepository: SpringDataWebtoonProductRepository
) : MakeWebtoonPort, ReadWebtoonProductPort {
    override fun makeWebtoon(webtoonProduct: WebtoonProduct) {
        TODO()
    }

    override fun getWebtoons(query: WebtoonProductQuery): List<WebtoonProduct> {
        return webtoonProductRepository.findAll(query).map {
            mapper.mapToEntity(it)
        }
    }

    override fun getSortedWebtoons(query: WebtoonProductSortQuery): List<WebtoonProduct> {
        return webtoonProductRepository.findAll(query).map {
            mapper.mapToEntity(it)
        }
    }

    override fun getWebtoon(query: WebtoonProductQuery): WebtoonProduct? {
        return webtoonProductRepository.find(query)?.let {
            mapper.mapToEntity(it)
        }
    }

    override fun getWebtoonCount(query: WebtoonProductQuery): Long {
        return webtoonProductRepository.count(query)
    }

    override fun getRandomWebtoons(query: ThreeWebtoonsQuery): List<WebtoonProduct> {
        val flattedDtos =
            webtoonProductRepository.findNthWebtoonsByDay(query.day?.value ?: "", query.randomRowNumbers)
        val dtosGroupedByWebtoonId = flattedDtos.groupBy { row -> (row[0] as Number).toLong() }

        val resultDtos = dtosGroupedByWebtoonId.map { (_, rows) ->
            val first = rows.first()

            WebtoonWithPersonDto(
                id = (first[0] as Number).toLong(),
                title = first[1] as String,
                description = first[2] as String,
                genreId = (first[3] as Number).toLong(),
                views = (first[4] as Number).toInt(),
                rating = first[5] as BigDecimal,
                likes = (first[6] as Number).toInt(),
                mainThumbnailLink = first[7] as String?,
                subThumbnailLink = first[8] as String?,
                dayOfWeek = first[9] as String,
                restrictedAge = first[10] as Int,
                status = first[11] as String,
                isDeleted = first[12] as Boolean,
                createdAt = first[13] as Timestamp,
                updatedAt = first[14] as Timestamp,
                creators = rows.map { row ->
                    CreatorDto(
                        personId = (row[15] as Number).toLong(),
                        name = row[16] as String,
                        role = row[17] as String
                    )
                }
            )
        }
        return resultDtos.map { dto ->
            mapper.mapToEntity(dto)
        }
    }
}