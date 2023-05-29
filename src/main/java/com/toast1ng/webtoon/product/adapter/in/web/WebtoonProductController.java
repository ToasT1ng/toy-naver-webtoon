package com.toast1ng.webtoon.product.adapter.in.web;

import com.toast1ng.webtoon.product.application.port.in.MakeWebtoonCommand;
import com.toast1ng.webtoon.product.application.port.in.MakeWebtoonUseCase;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;

@RequiredArgsConstructor
@RestController
public class WebtoonProductController {
    private final MakeWebtoonUseCase makeWebtoonUseCase;

    @PutMapping("/webtoon")
    public String writeWebtoon(WriteWebtoonVO vo) {
        makeWebtoonUseCase.makeWebtoon(MakeWebtoonCommand.builder()
                .title(vo.getTitle())
                .authorId(vo.getAuthorId())
                .day(vo.getDay())
                .build());
        return "success";
    }

    @Getter
    class WriteWebtoonVO {
        private String title;
        private String authorId;
        private DayOfWeek day;
    }
}
