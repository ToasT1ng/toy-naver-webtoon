package com.toast1ng.webtoon.member.application.port.out

import com.toast1ng.webtoon.member.application.port.out.cache.BlacklistCache

interface StoreBlacklistTokenPort{
    fun add(command: BlacklistCache)
}