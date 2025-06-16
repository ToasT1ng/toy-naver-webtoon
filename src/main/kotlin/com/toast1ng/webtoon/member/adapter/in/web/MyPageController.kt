package com.toast1ng.webtoon.member.adapter.`in`.web

import com.toast1ng.webtoon.common.domain.annotations.LoginUser
import com.toast1ng.webtoon.common.response.ResponseEntityFactory
import com.toast1ng.webtoon.common.response.SuccessResponse
import com.toast1ng.webtoon.member.domain.User
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MyPageController {
    @GetMapping("/mypage")
    fun myPageTest(@LoginUser user: User): ResponseEntity<SuccessResponse<Any?>> {
        println(user.username)
        return ResponseEntityFactory.success()
    }
}