package com.toast1ng.webtoon.member.application.port.out

import com.toast1ng.webtoon.member.application.port.out.cache.WhitelistCache

interface StoreWhitelistTokenPort{
    fun add(command: WhitelistCache)
}