package com.toast1ng.webtoon.product.adapter.out.persistence

import com.toast1ng.webtoon.test.annotation.MySpringBootTest
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@MySpringBootTest
class WebtoonEpisodeCommentPersistenceAdapterTest @Autowired constructor(
    private val webtoonEpisodeCommentPersistenceAdapter: WebtoonEpisodeCommentPersistenceAdapter,
){
    @Test
    @DisplayName("에피소드에 달린 상위 댓글 리스트 DB상에서 불러오기")
    fun getTopCommentsByEpisodeId() {
        // given
        val episodeId = 1L

        // when
        val comments = webtoonEpisodeCommentPersistenceAdapter.findTopCommentsByEpisodeId(episodeId)

        // then
        comments.size shouldBe 4
    }

    @Test
    @DisplayName("특정 댓글의 대댓글 리스트 DB상에서 불러오기")
    fun getChildCommentsByParentId() {
        // given
        val parentId = 1L

        // when
        val comments = webtoonEpisodeCommentPersistenceAdapter.findChildCommentsByParentId(parentId)

        // then
        comments.size shouldBe 2
        comments[0].id shouldBe 3
        comments[1].id shouldBe 4
    }
}