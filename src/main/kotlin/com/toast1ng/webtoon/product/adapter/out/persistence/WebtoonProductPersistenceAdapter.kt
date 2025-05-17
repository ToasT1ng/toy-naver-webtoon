package com.toast1ng.webtoon.product.adapter.out.persistence

import com.toast1ng.webtoon.common.PersistenceAdapter
import com.toast1ng.webtoon.product.application.port.out.*
import com.toast1ng.webtoon.product.domain.WebtoonProduct
import java.math.BigDecimal
import java.sql.Timestamp

//TODO
// 1. n+1 문제 해결
// 2. queryDSL로 변경
@PersistenceAdapter
class WebtoonProductPersistenceAdapter(
    private val mapper: WebtoonProductMapper,
    private val webtoonProductRepository: SpringDataWebtoonProductRepository
) : MakeWebtoonPort, ReadWebtoonProductPort {
    override fun makeWebtoon(webtoonProduct: WebtoonProduct) {
        TODO()
    }

    override fun getWebtoons(query: GetWebtoonsQuery): List<WebtoonProduct> {
        return (query.day?.value?.let {
            webtoonProductRepository.findAllByDay(it)
        } ?: webtoonProductRepository.findAll())
            .map {
                mapper.mapToEntity(it)
            }
    }

    override fun getWebtoon(query: GetWebtoonQuery): WebtoonProduct? {
        return webtoonProductRepository.findById(query.id!!).map {
            mapper.mapToEntity(it)
        }.orElse(null)
    }

    override fun getWebtoonCount(query: GetWebtoonsQuery): Long {
        return webtoonProductRepository.countByDay(query.day?.value ?: "")
    }

    override fun getRandomThreeWebtoon(query: GetThreeWebtoonsQuery): List<WebtoonProduct> {
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
                mainThumbnailLink = first[6] as String?,
                subThumbnailLink = first[7] as String?,
                dayOfWeek = first[8] as String,
                status = first[9] as String,
                isDeleted = first[10] as Boolean,
                createdAt = first[11] as Timestamp,
                updatedAt = first[12] as Timestamp,
                creators = rows.map { row ->
                    CreatorDto(
                        personId = (row[13] as Number).toLong(),
                        name = row[14] as String,
                        role = row[15] as String
                    )
                }
            )
        }
        return resultDtos.map { dto ->
            mapper.mapToEntity(dto)
        }
    }
}