package com.toast1ng.webtoon.product.application.service

import com.toast1ng.webtoon.product.application.port.`in`.GetWebtoonViewUseCase
import com.toast1ng.webtoon.product.application.port.`in`.UpdateWebtoonViewUseCase
import com.toast1ng.webtoon.product.application.port.`in`.command.UpdateWebtoonViewsCommand
import com.toast1ng.webtoon.product.application.port.out.ReadEpisodeViewsPort
import com.toast1ng.webtoon.product.application.port.out.UpdateEpisodeViewsPort
import com.toast1ng.webtoon.product.domain.EpisodeView
import com.toast1ng.webtoon.product.domain.WebtoonView
import org.springframework.stereotype.Service

//TODO
// 1. Read & Command 분리
// 2. 테스트 코드 작성
@Service
class WebtoonEpisodeViewsService(
    private val updateEpisodeViewsPort: UpdateEpisodeViewsPort,
    private val readEpisodeViewsPort: ReadEpisodeViewsPort,
) : UpdateWebtoonViewUseCase, GetWebtoonViewUseCase {

    override fun updateWebtoonViews(command: UpdateWebtoonViewsCommand) {
        updateEpisodeViewsPort.increaseEpisodeViews(command.episodeId)
        updateEpisodeViewsPort.increaseWebtoonViews(command.webtoonId)
        //TODO 사용자 ID로 조회수 증가
        //TODO IP로 조회수 증가?
    }

    override fun getWebtoonRanking(webtoonId: Long): List<WebtoonView> {
        return readEpisodeViewsPort.getWebtoonRanking(webtoonId, 24).map { (id, views) ->
            WebtoonView(id, views)
        }
    }

    override fun getEpisodeRanking(episodeId: Long): List<EpisodeView> {
        return readEpisodeViewsPort.getEpisodeRanking(episodeId, 24).map { (id, views) ->
            EpisodeView(id, views)
        }
    }
}