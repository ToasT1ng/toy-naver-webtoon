package com.toast1ng.webtoon.product.adapter.out.persistence;

import com.toast1ng.webtoon.common.PersistenceAdapter;
import com.toast1ng.webtoon.product.application.port.out.MakeWebtoonPort;
import com.toast1ng.webtoon.product.domain.WebtoonProduct;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class WebtoonProductPersistenceAdapter implements MakeWebtoonPort {
    private final WebtoonProductMapper mapper;
    private final SpringDataWebtoonProductRepository webtoonProductRepository;

    @Override
    public void makeWebtoon(WebtoonProduct webtoonProduct) {
        webtoonProductRepository.save(mapper.mapToJpaEntity(webtoonProduct));
    }
}
