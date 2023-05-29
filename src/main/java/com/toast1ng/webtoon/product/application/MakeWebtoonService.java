package com.toast1ng.webtoon.product.application;

import com.toast1ng.webtoon.product.application.port.in.MakeWebtoonCommand;
import com.toast1ng.webtoon.product.application.port.in.MakeWebtoonUseCase;
import com.toast1ng.webtoon.product.application.port.out.MakeWebtoonPort;
import com.toast1ng.webtoon.product.domain.WebtoonProduct;
import com.toast1ng.webtoon.product.domain.WebtoonStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MakeWebtoonService implements MakeWebtoonUseCase {
    private final MakeWebtoonPort port;

    @Override
    public void makeWebtoon(MakeWebtoonCommand command) {
        port.makeWebtoon(WebtoonProduct.builder()
                .id(UUID.randomUUID().toString())
                .title(command.getTitle())
                .authorId(command.getAuthorId())
                .views(0)
                .rating(new BigDecimal("0.0"))
                .day(command.getDay())
                .status(WebtoonStatus.serialize)
                .build());
    }
}
