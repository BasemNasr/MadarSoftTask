package com.bn.madarsofttaskbassemnar.domain.usecase

import com.bn.madarsofttaskbassemnar.domain.model.User
import com.bn.madarsofttaskbassemnar.domain.repository.UserRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class DeleteUserUseCaseTest {

    private lateinit var repository: UserRepository
    private lateinit var deleteUserUseCase: DeleteUserUseCase

    @Before
    fun setup() {
        repository = mockk()
        deleteUserUseCase = DeleteUserUseCase(repository)
    }

    @Test
    fun `invoke calls repository deleteUser`() = runTest {
        val user = User(id = 1, name = "John Doe", jobTitle = "Developer", age = 30, gender = "MALE")
        coEvery { repository.deleteUser(user) } returns Unit

        deleteUserUseCase(user)

        coVerify { repository.deleteUser(user) }
    }
}
