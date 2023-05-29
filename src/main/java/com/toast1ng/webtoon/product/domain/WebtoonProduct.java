package com.toast1ng.webtoon.product.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.DayOfWeek;

@Data
public class WebtoonProduct {
    private String id;
    private String title;
    private String authorId;
    private int views;
    private BigDecimal rating;
    private DayOfWeek day;
    private WebtoonStatus status;

    @Builder
    public WebtoonProduct(String id, String title, String authorId, int views, BigDecimal rating, DayOfWeek day, WebtoonStatus status) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.views = views;
        this.rating = rating;
        this.day = day;
        this.status = status;
    }
}
