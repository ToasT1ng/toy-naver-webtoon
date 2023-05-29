package com.toast1ng.webtoon.product.adapter.out.persistence;

import com.toast1ng.webtoon.product.domain.WebtoonStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.Date;

@Data
public class WebtoonProductJpaEntity {
    private String id;
    private String title;
    private String authorId;
    private int views;
    private BigDecimal rating;
    private Date updatedDate;
    private DayOfWeek day;
    private WebtoonStatus status;
    private boolean isDeleteFlag;
}
