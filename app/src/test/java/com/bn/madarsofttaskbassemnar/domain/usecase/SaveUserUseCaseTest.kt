package com.bn.madarsofttaskbassemnar.domain.usecase

import com.bn.madarsofttaskbassemnar.domain.model.User
import com.bn.madarsofttaskbassemnar.domain.repository.UserRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SaveUserUseCaseTest {

    private lateinit var repository: UserRepository
    private lateinit var saveUserUseCase: SaveUserUseCase

    @Before
    fun setup() {
        repository = mockk()
        saveUserUseCase = SaveUserUseCase(repository)
    }

    @Test
    fun `invoke calls repository saveUser`() = runTest {
        val user = User(id = 1, name = "John Doe", jobTitle = "Developer", age = 30, gender = "MALE")
        coEvery { repository.saveUser(user) } returns Unit

        saveUserUseCase(user)

        coVerify { repository.saveUser(user) }
    }
}
