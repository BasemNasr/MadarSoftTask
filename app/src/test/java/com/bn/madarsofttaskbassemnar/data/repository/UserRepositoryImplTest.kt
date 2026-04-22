package com.bn.madarsofttaskbassemnar.data.repository

import com.bn.madarsofttaskbassemnar.data.local.UserDao
import com.bn.madarsofttaskbassemnar.data.local.entity.UserEntity
import com.bn.madarsofttaskbassemnar.domain.model.User
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UserRepositoryImplTest {

    private lateinit var userDao: UserDao
    private lateinit var repository: UserRepositoryImpl

    @Before
    fun setup() {
        userDao = mockk()
        repository = UserRepositoryImpl(userDao)
    }

    @Test
    fun `getAllUsers returns mapped domain users`() = runTest {
        val userEntities = listOf(
            UserEntity(id = 1, name = "John Doe", jobTitle = "Developer", age = 30, gender = "MALE")
        )
        coEvery { userDao.getAllUsers() } returns flowOf(userEntities)

        val result = repository.getAllUsers().first()

        assertEquals(1, result.size)
        assertEquals("John Doe", result[0].name)
    }

    @Test
    fun `saveUser inserts mapped user entity`() = runTest {
        val user = User(id = 1, name = "John Doe", jobTitle = "Developer", age = 30, gender = "MALE")
        coEvery { userDao.insertUser(any()) } returns Unit

        repository.saveUser(user)

        coVerify { 
            userDao.insertUser(match { 
                it.id == 1L && it.name == "John Doe" 
            }) 
        }
    }

    @Test
    fun `deleteUser deletes mapped user entity`() = runTest {
        val user = User(id = 1, name = "John Doe", jobTitle = "Developer", age = 30, gender = "MALE")
        coEvery { userDao.deleteUser(any()) } returns Unit

        repository.deleteUser(user)

        coVerify { 
            userDao.deleteUser(match { 
                it.id == 1L && it.name == "John Doe" 
            }) 
        }
    }
}
