package com.bn.madarsofttaskbassemnar.domain.usecase

import com.bn.madarsofttaskbassemnar.domain.model.User
import com.bn.madarsofttaskbassemnar.domain.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetAllUsersUseCaseTest {

    private lateinit var repository: UserRepository
    private lateinit var getAllUsersUseCase: GetAllUsersUseCase

    @Before
    fun setup() {
        repository = mockk()
        getAllUsersUseCase = GetAllUsersUseCase(repository)
    }

    @Test
    fun `invoke calls repository getAllUsers`() = runTest {
        val users = listOf(
            User(id = 1, name = "John Doe", jobTitle = "Developer", age = 30, gender = "MALE")
        )
        every { repository.getAllUsers() } returns flowOf(users)

        val result = getAllUsersUseCase().first()

        assertEquals(users, result)
    }
}
