package com.toast1ng.webtoon.product.application.port

import com.toast1ng.webtoon.product.application.port.`in`.MakeWebtoonCommand
import com.toast1ng.webtoon.product.application.port.`in`.MakeWebtoonUseCase
import com.toast1ng.webtoon.product.application.port.out.MakeWebtoonPort
import com.toast1ng.webtoon.product.domain.WebtoonProduct
import com.toast1ng.webtoon.product.domain.WebtoonStatus
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.UUID

@Service
class MakeWebtoonService(
    private val port: MakeWebtoonPort
) : MakeWebtoonUseCase {
    override fun makeWebtoon(command: MakeWebtoonCommand) {
        port.makeWebtoon(
            WebtoonProduct(
                id = UUID.randomUUID().toString(),
                title = command.title,
                authorId = command.authorId,
                views = 0,
                rating = BigDecimal("0.0"),
                day = command.day,
                status = WebtoonStatus.serialize
            )
        )
    }
}