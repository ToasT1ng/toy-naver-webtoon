package com.toast1ng.webtoon.product.adapter.`in`.web

import com.toast1ng.webtoon.common.response.ResponseEntityFactory
import com.toast1ng.webtoon.common.response.SuccessResponse
import com.toast1ng.webtoon.product.adapter.`in`.web.response.WebtoonNavigationResponse
import com.toast1ng.webtoon.product.adapter.`in`.web.response.toResponse
import com.toast1ng.webtoon.product.application.port.`in`.command.GetWebtoonEpisodeNavigationCommand
import com.toast1ng.webtoon.product.application.service.WebtoonEpisodeNavigationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ReadWebtoonEpisodeNavigationController (
    private val webtoonEpisodeNavigationService: WebtoonEpisodeNavigationService,
) {
    @GetMapping("/webtoons/{webtoonId}/episodes/{episodeId}/navigation")
    fun getWebtoonEpisodeNavigation(
        @PathVariable webtoonId: Long,
        @PathVariable episodeId: Long
    ): ResponseEntity<SuccessResponse<WebtoonNavigationResponse>> {
        val command = GetWebtoonEpisodeNavigationCommand(
            webtoonId = webtoonId,
            episodeId = episodeId
        )
        val result = webtoonEpisodeNavigationService.getWebtoonEpisodeNavigation(command)
        return ResponseEntityFactory.success(result.toResponse())
    }

}
