package com.bn.madarsofttaskbassemnar.presentation.input

import app.cash.turbine.test
import com.bn.madarsofttaskbassemnar.MainDispatcherRule
import com.bn.madarsofttaskbassemnar.domain.usecase.SaveUserUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.coVerify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class InputViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var saveUserUseCase: SaveUserUseCase
    private lateinit var viewModel: InputViewModel

    @Before
    fun setup() {
        saveUserUseCase = mockk()
        viewModel = InputViewModel(saveUserUseCase)
    }

    @Test
    fun `when fields are updated, state changes`() = runTest {
        viewModel.onNameChange("John Doe")
        viewModel.onJobTitleChange("Developer")
        viewModel.onAgeChange("30")
        viewModel.onGenderChange("FEMALE")

        viewModel.uiState.test {
            val item = awaitItem()
            assertEquals("John Doe", item.name)
            assertEquals("Developer", item.jobTitle)
            assertEquals("30", item.age)
            assertEquals("FEMALE", item.gender)
            expectNoEvents()
        }
    }

    @Test
    fun `when saveUser with empty fields, error is set`() = runTest {
        viewModel.saveUser()
        
        viewModel.uiState.test {
            val item = awaitItem()
            assertNotNull(item.error)
            expectNoEvents()
        }
    }

    @Test
    fun `when saveUser with invalid age, error is set`() = runTest {
        viewModel.onNameChange("John")
        viewModel.onJobTitleChange("Dev")
        viewModel.onAgeChange("abc")
        
        viewModel.saveUser()
        
        viewModel.uiState.test {
            val item = awaitItem()
            assertNotNull(item.error)
            expectNoEvents()
        }
    }

    @Test
    fun `when saveUser with valid fields, use case is called and isSaved is true`() = runTest {
        viewModel.onNameChange("John")
        viewModel.onJobTitleChange("Dev")
        viewModel.onAgeChange("30")
        
        coEvery { saveUserUseCase(any()) } returns Unit
        
        viewModel.saveUser()
        
        viewModel.uiState.test {
            val item = awaitItem()
            assertEquals(true, item.isSaved)
            assertNull(item.error)
            expectNoEvents()
        }
        
        coVerify { saveUserUseCase(any()) }
    }
}
