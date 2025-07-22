package com.toast1ng.webtoon.member.adapter.`in`.web

import com.toast1ng.webtoon.common.domain.annotations.LoginUser
import com.toast1ng.webtoon.common.response.ResponseEntityFactory
import com.toast1ng.webtoon.common.response.SuccessResponse
import com.toast1ng.webtoon.member.adapter.`in`.web.request.UpdateUserLikedWebtoonsRequest
import com.toast1ng.webtoon.member.adapter.`in`.web.request.toDomainStatus
import com.toast1ng.webtoon.member.adapter.`in`.web.response.UserLikedWebtoonResponse
import com.toast1ng.webtoon.member.adapter.`in`.web.response.UserLikedWebtoonsResponse
import com.toast1ng.webtoon.member.adapter.`in`.web.response.toResponse
import com.toast1ng.webtoon.member.application.port.`in`.GetWebtoonLikeUseCase
import com.toast1ng.webtoon.member.application.port.`in`.ToggleWebtoonLikeUseCase
import com.toast1ng.webtoon.member.domain.User
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserLikedWebtoonController(
    private val getWebtoonLikeUseCase: GetWebtoonLikeUseCase,
    private val toggleWebtoonLikeUseCase: ToggleWebtoonLikeUseCase,
) {
    @GetMapping("/users/webtoon-likes")
    fun getUserLikedWebtoons(@LoginUser user: User): ResponseEntity<SuccessResponse<UserLikedWebtoonsResponse>> {
        val result = getWebtoonLikeUseCase.getUserLikedWebtoons(user.id)
        return ResponseEntityFactory.success(UserLikedWebtoonsResponse(result.map { it.toResponse() }))
    }

    @GetMapping("/users/webtoon-likes/{webtoonId}")
    fun getUserLikedWebtoon(
        @LoginUser user: User,
        @PathVariable webtoonId: Long
    ): ResponseEntity<SuccessResponse<UserLikedWebtoonResponse>> {
        val result = getWebtoonLikeUseCase.getUserLikedWebtoon(user.id, webtoonId)
        return ResponseEntityFactory.success(result.toResponse())
    }

    @PostMapping("/users/webtoon-likes/{webtoonId}")
    fun updateUserLikedWebtoons(
        @LoginUser user: User,
        @PathVariable webtoonId: Long,
        @RequestBody request: UpdateUserLikedWebtoonsRequest
    ): ResponseEntity<SuccessResponse<Any?>> {
        toggleWebtoonLikeUseCase.toggleLike(user, webtoonId, request.status.toDomainStatus())
        return ResponseEntityFactory.success()
    }
}