package com.toast1ng.webtoon.product.adapter.out.persistence

import com.toast1ng.webtoon.product.domain.WebtoonProduct
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class WebtoonProductMapper {
    fun mapToJpaEntity(webtoonProduct: WebtoonProduct): WebtoonProductJpaEntity {
        return WebtoonProductJpaEntity(
            id = webtoonProduct.id,
            title = webtoonProduct.title,
            authorId = webtoonProduct.authorId,
            views = webtoonProduct.views,
            rating = webtoonProduct.rating,
            updatedDate = LocalDateTime.now(),
            day = webtoonProduct.day,
            status = webtoonProduct.status,
            isDeleteFlag = false,
        )
    }
}