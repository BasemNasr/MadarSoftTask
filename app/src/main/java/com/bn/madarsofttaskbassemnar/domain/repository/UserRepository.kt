package com.bn.madarsofttaskbassemnar.domain.repository

import com.bn.madarsofttaskbassemnar.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAllUsers(): Flow<List<User>>
    suspend fun saveUser(user: User)
    suspend fun deleteUser(user: User)
}
