package com.toast1ng.webtoon.product.adapter.`in`.web

import com.toast1ng.webtoon.product.adapter.`in`.web.request.WriteWebtoonVO
import com.toast1ng.webtoon.product.application.port.`in`.MakeWebtoonCommand
import com.toast1ng.webtoon.product.application.port.`in`.MakeWebtoonUseCase
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WriteWebtoonProductController(private val makeWebtoonUseCase: MakeWebtoonUseCase) {

    @PutMapping("/webtoon")
    fun writeWebtoon(vo: WriteWebtoonVO): String {
        makeWebtoonUseCase.makeWebtoon(
            MakeWebtoonCommand(
                title = vo.title,
                authorId = vo.authorId,
                day = vo.day
            )
        )
        return "success"
    }

}