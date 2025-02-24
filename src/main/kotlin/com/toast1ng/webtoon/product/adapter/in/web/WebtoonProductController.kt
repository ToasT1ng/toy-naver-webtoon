package com.toast1ng.webtoon.product.adapter.`in`.web

import com.toast1ng.webtoon.product.application.port.`in`.MakeWebtoonCommand
import com.toast1ng.webtoon.product.application.port.`in`.MakeWebtoonUseCase
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController
import java.time.DayOfWeek

@RestController
class WebtoonProductController(private val makeWebtoonUseCase: MakeWebtoonUseCase) {

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

    data class WriteWebtoonVO (
        var title: String,
        var authorId: String,
        var day: DayOfWeek,
    )
}