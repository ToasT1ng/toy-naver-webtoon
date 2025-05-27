package com.toast1ng.webtoon.product.adapter.`in`.web

import com.toast1ng.webtoon.product.adapter.`in`.web.request.PagingWebtoonEpisodesRequest
import com.toast1ng.webtoon.product.adapter.`in`.web.request.toCommand
import com.toast1ng.webtoon.product.adapter.`in`.web.response.PagingWebtoonEpisodesResponse
import com.toast1ng.webtoon.product.adapter.`in`.web.response.toResponse
import com.toast1ng.webtoon.product.application.port.`in`.GetWebtoonEpisodeUseCase
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ReadWebtoonEpisodeController(
    private val liveWebtoonEpisodeReadService: GetWebtoonEpisodeUseCase,
) {
    @GetMapping("/webtoons/{webtoonId}/episodes")
    fun getWebtoonEpisodes(
        @Valid request: PagingWebtoonEpisodesRequest,
        @PathVariable webtoonId: Long
    ): ResponseEntity<PagingWebtoonEpisodesResponse> {
        val result = liveWebtoonEpisodeReadService.getWebtoonEpisodes(request.toCommand(webtoonId))
        return ResponseEntity.ok().body(
            PagingWebtoonEpisodesResponse(
                webtoonId = webtoonId,
                pageNo = result.number + 1,
                pageSize = result.size,
                totalCount = result.totalElements,
                totalPage = result.totalPages,
                isLastPage = result.isLast,
                content = result.content.map { it.toResponse() }
            )
        )
    }
}