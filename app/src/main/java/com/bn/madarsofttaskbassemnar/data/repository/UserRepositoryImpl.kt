package com.bn.madarsofttaskbassemnar.data.repository

import com.bn.madarsofttaskbassemnar.data.local.UserDao
import com.bn.madarsofttaskbassemnar.data.local.entity.toDomain
import com.bn.madarsofttaskbassemnar.data.local.entity.toEntity
import com.bn.madarsofttaskbassemnar.domain.model.User
import com.bn.madarsofttaskbassemnar.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override fun getAllUsers(): Flow<List<User>> {
        return userDao.getAllUsers().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun saveUser(user: User) {
        userDao.insertUser(user.toEntity())
    }

    override suspend fun deleteUser(user: User) {
        userDao.deleteUser(user.toEntity())
    }
}
