package com.toast1ng.webtoon.product.application.port.out;

import com.toast1ng.webtoon.product.domain.WebtoonProduct;

public interface MakeWebtoonPort {
    void makeWebtoon(WebtoonProduct webtoonProduct);
}
