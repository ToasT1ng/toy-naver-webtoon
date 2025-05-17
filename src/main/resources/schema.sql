CREATE TABLE webtoon
(
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    title               VARCHAR(255) NOT NULL,
    description         TEXT         NOT NULL,
    genre_id            BIGINT       NOT NULL,
    views               BIGINT                DEFAULT 0,
    rating              DECIMAL(3, 2)         DEFAULT 0.00,
    main_thumbnail_link VARCHAR(500),
    sub_thumbnail_link  VARCHAR(500),
    day_of_week         VARCHAR(5)   NOT NULL,
    status              VARCHAR(20)  NOT NULL,
    is_deleted          BOOLEAN      NOT NULL DEFAULT FALSE,
    created_at          TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP             DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE genre
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(50) NOT NULL UNIQUE,
    description TEXT
);

CREATE TABLE person
(
    id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    role VARCHAR(20)  NOT NULL
);

CREATE TABLE creator
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    webtoon_id VARCHAR(50) NOT NULL,
    person_id  BIGINT      NOT NULL,
    FOREIGN KEY (webtoon_id) REFERENCES webtoon (id),
    FOREIGN KEY (person_id) REFERENCES person (id)
);

CREATE TABLE webtoon_image
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    webtoon_id VARCHAR(50)  NOT NULL,
    seq        INT          NOT NULL,
    img_link   VARCHAR(500) NOT NULL,
    FOREIGN KEY (webtoon_id) REFERENCES webtoon (id)
);