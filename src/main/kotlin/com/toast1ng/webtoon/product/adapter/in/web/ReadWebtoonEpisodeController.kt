package com.toast1ng.webtoon.product.adapter.`in`.web

import com.toast1ng.webtoon.common.domain.annotations.LoginUser
import com.toast1ng.webtoon.common.response.ResponseEntityFactory
import com.toast1ng.webtoon.common.response.SuccessResponse
import com.toast1ng.webtoon.member.domain.User
import com.toast1ng.webtoon.product.adapter.`in`.web.request.PagingWebtoonEpisodesRequest
import com.toast1ng.webtoon.product.adapter.`in`.web.request.toCommand
import com.toast1ng.webtoon.product.adapter.`in`.web.response.PagingWebtoonEpisodesResponse
import com.toast1ng.webtoon.product.adapter.`in`.web.response.WebtoonEpisodeResponse
import com.toast1ng.webtoon.product.adapter.`in`.web.response.toBriefResponse
import com.toast1ng.webtoon.product.adapter.`in`.web.response.toResponse
import com.toast1ng.webtoon.product.application.port.`in`.GetWebtoonEpisodeUseCase
import com.toast1ng.webtoon.product.application.port.`in`.command.GetWebtoonEpisodeCommand
import com.toast1ng.webtoon.product.application.port.`in`.command.UpdateWebtoonViewsCommand
import com.toast1ng.webtoon.product.application.service.WebtoonEpisodeViewsService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ReadWebtoonEpisodeController(
    private val liveWebtoonEpisodeReadService: GetWebtoonEpisodeUseCase,
    private val webtoonEpisodeViewsService: WebtoonEpisodeViewsService,
) {
    @GetMapping("/webtoons/{webtoonId}/episodes")
    fun getWebtoonEpisodes(
        @Valid request: PagingWebtoonEpisodesRequest,
        @PathVariable webtoonId: Long
    ): ResponseEntity<SuccessResponse<PagingWebtoonEpisodesResponse>> {
        val result = liveWebtoonEpisodeReadService.getWebtoonEpisodes(request.toCommand(webtoonId))
        return ResponseEntityFactory.success(
            PagingWebtoonEpisodesResponse(
                webtoonId = webtoonId,
                pageNo = result.number + 1,
                pageSize = result.size,
                totalCount = result.totalElements,
                totalPage = result.totalPages,
                isLastPage = result.isLast,
                content = result.content.map { it.toBriefResponse() }
            )
        )
    }

    @GetMapping("/webtoons/{webtoonId}/episodes/{episodeId}")
    fun getWebtoonEpisode(
        @PathVariable webtoonId: Long,
        @PathVariable episodeId: Long,
        @LoginUser user: User? = null
    ): ResponseEntity<SuccessResponse<WebtoonEpisodeResponse>> {
        if (user != null) {
            webtoonEpisodeViewsService.updateWebtoonViews(
                UpdateWebtoonViewsCommand(
                    userId = user.id,
                    episodeId = episodeId,
                    webtoonId = webtoonId
                )
            )
        }
        val result = liveWebtoonEpisodeReadService.getWebtoonEpisode(
            GetWebtoonEpisodeCommand(
                id = episodeId,
                webtoonId = webtoonId
            )
        )
        return ResponseEntityFactory.success(result.toResponse())
    }

}