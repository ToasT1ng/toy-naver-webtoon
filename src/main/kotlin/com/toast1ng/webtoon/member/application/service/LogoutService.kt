package com.toast1ng.webtoon.member.application.service

import com.toast1ng.webtoon.member.application.port.`in`.LogoutUserUseCase
import org.springframework.stereotype.Service

@Service
class LogoutService : LogoutUserUseCase {
    override fun logout(accessToken: String) {
        //TODO accessToken을 REDIS 블랙리스트에 추가하여 로그아웃 처리
    }
}