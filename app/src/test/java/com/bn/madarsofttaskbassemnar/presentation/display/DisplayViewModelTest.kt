package com.bn.madarsofttaskbassemnar.presentation.display

import app.cash.turbine.test
import com.bn.madarsofttaskbassemnar.MainDispatcherRule
import com.bn.madarsofttaskbassemnar.domain.model.User
import com.bn.madarsofttaskbassemnar.domain.usecase.DeleteUserUseCase
import com.bn.madarsofttaskbassemnar.domain.usecase.GetAllUsersUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.coVerify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DisplayViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var getAllUsersUseCase: GetAllUsersUseCase
    private lateinit var deleteUserUseCase: DeleteUserUseCase
    private lateinit var viewModel: DisplayViewModel

    @Before
    fun setup() {
        getAllUsersUseCase = mockk()
        deleteUserUseCase = mockk()
    }

    private fun createViewModel() {
        viewModel = DisplayViewModel(getAllUsersUseCase, deleteUserUseCase)
    }

    @Test
    fun `init emits users from use case`() = runTest {
        val usersList = listOf(User(id = 1, name = "John", jobTitle = "Dev", age = 30, gender = "MALE"))
        every { getAllUsersUseCase() } returns flowOf(usersList)
        
        createViewModel()
        
        viewModel.users.test {
            assertEquals(usersList, awaitItem()) // Emitted by flow
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `deleteUser calls usecase`() = runTest {
        every { getAllUsersUseCase() } returns flowOf(emptyList())
        coEvery { deleteUserUseCase(any()) } returns Unit
        createViewModel()

        val user = User(id = 1, name = "John", jobTitle = "Dev", age = 30, gender = "MALE")
        viewModel.deleteUser(user)
        
        advanceUntilIdle()

        coVerify { deleteUserUseCase(user) }
    }
}
