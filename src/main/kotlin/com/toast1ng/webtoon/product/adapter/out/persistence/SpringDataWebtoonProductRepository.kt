package com.toast1ng.webtoon.product.adapter.out.persistence

import com.toast1ng.webtoon.product.adapter.out.persistence.entity.WebtoonProductJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.querydsl.QueryDslWebtoonProductRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface SpringDataWebtoonProductRepository : JpaRepository<WebtoonProductJpaEntity, Long>, QueryDslWebtoonProductRepository {
    @Query(
        value = """
        SELECT
                w.id, w.title, w.description, w.genre_id, w.views,
                w.rating, w.main_thumbnail_link, w.sub_thumbnail_link,
                w.day_of_week, w.status, w.is_deleted, w.created_at, w.updated_at,
                p.id AS person_id, p.name AS person_name, p.role AS person_role
            FROM (
                SELECT *, ROW_NUMBER() OVER (ORDER BY id) AS rn
                FROM webtoon
                WHERE day_of_week = :dayOfWeek
            ) w
            JOIN creator c ON c.webtoon_id = w.id
            JOIN person p ON p.id = c.person_id
            WHERE w.rn IN (:rows)
            ORDER BY w.id
    """,
        nativeQuery = true
    )
    fun findNthWebtoonsByDay(
        @Param("dayOfWeek") dayOfWeek: String,
        @Param("rows") rows: List<Long>
    ): List<Array<Any>>
}