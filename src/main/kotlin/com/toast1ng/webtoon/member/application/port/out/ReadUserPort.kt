package com.toast1ng.webtoon.member.application.port.out

import com.toast1ng.webtoon.member.application.port.out.query.UserQuery
import com.toast1ng.webtoon.member.domain.User

interface ReadUserPort {
    fun getUser(query: UserQuery): User?
    fun isUserExists(query: UserQuery): Boolean
}