package com.toast1ng.webtoon.product.adapter.`in`.web

import com.toast1ng.webtoon.common.response.ResponseEntityFactory
import com.toast1ng.webtoon.common.response.SuccessResponse
import com.toast1ng.webtoon.product.adapter.`in`.web.request.PagingWebtoonEpisodeCommentRequest
import com.toast1ng.webtoon.product.adapter.`in`.web.response.WebtoonEpisodeCommentsResponse
import com.toast1ng.webtoon.product.adapter.`in`.web.response.toResponse
import com.toast1ng.webtoon.product.application.port.`in`.GetWebtoonEpisodeCommentUseCase
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ReadWebtoonEpisodeCommentController(
    private val getWebtoonEpisodeCommentUseCase: GetWebtoonEpisodeCommentUseCase,
) {
    @GetMapping("/webtoons/{webtoonId}/episodes/{episodeId}/comments")
    fun getWebtoonEpisodeComments(
        @PathVariable webtoonId: Long,
        @PathVariable episodeId: Long,
        @Valid request: PagingWebtoonEpisodeCommentRequest, //TODO paging 처리
    ): ResponseEntity<SuccessResponse<WebtoonEpisodeCommentsResponse>> {
        val result = getWebtoonEpisodeCommentUseCase.getTopCommentsByEpisodeId(
            episodeId = episodeId
        )
        return ResponseEntityFactory.success(
            WebtoonEpisodeCommentsResponse(content = result.map { it.toResponse() })
        )
    }

    @GetMapping("/webtoons/{webtoonId}/episodes/{episodeId}/comments/{commentId}")
    fun getChildCommentsByParentId(
        @PathVariable webtoonId: Long,
        @PathVariable episodeId: Long,
        @PathVariable commentId: Long,
        @Valid request: PagingWebtoonEpisodeCommentRequest, //TODO paging 처리
    ): ResponseEntity<SuccessResponse<WebtoonEpisodeCommentsResponse>> {
        val result = getWebtoonEpisodeCommentUseCase.getChildCommentsByParentId(
            parentId = commentId
        )
        return ResponseEntityFactory.success(
            WebtoonEpisodeCommentsResponse(content = result.map { it.toResponse() })
        )
    }
}