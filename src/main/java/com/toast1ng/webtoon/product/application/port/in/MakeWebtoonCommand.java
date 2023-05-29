package com.toast1ng.webtoon.product.application.port.in;

import lombok.Builder;
import lombok.Data;

import java.time.DayOfWeek;

@Data
public class MakeWebtoonCommand {
    private String title;
    private String authorId;
    private DayOfWeek day;

    @Builder
    public MakeWebtoonCommand(String title, String authorId, DayOfWeek day) {
        this.title = title;
        this.authorId = authorId;
        this.day = day;
    }
}
