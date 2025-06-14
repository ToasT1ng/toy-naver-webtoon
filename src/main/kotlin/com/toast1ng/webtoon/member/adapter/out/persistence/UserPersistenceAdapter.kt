package com.toast1ng.webtoon.member.adapter.out.persistence

import com.toast1ng.webtoon.common.domain.PersistenceAdapter
import com.toast1ng.webtoon.member.application.port.out.ReadUserPort
import com.toast1ng.webtoon.member.application.port.out.RegisterUserPort
import com.toast1ng.webtoon.member.application.port.out.query.UserQuery
import com.toast1ng.webtoon.member.domain.User

@PersistenceAdapter
class UserPersistenceAdapter(
    private val springDataUserRepository: SpringDataUserRepository,
    private val mapper: UserMapper
): ReadUserPort, RegisterUserPort {
    override fun getUser(query: UserQuery): User? {
        return springDataUserRepository.find(query)?.let {
            mapper.mapToEntity(it)
        }
    }

    override fun isUserExists(query: UserQuery): Boolean {
        return springDataUserRepository.exists(query)
    }

    override fun registerUser(user: User): User {
        return springDataUserRepository.save(mapper.mapToJpaEntity(user))
            .let { mapper.mapToEntity(it) }
    }
}