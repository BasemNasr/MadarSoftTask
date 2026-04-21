package com.bn.madarsofttaskbassemnar.presentation.input

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bn.madarsofttaskbassemnar.domain.model.User
import com.bn.madarsofttaskbassemnar.domain.usecase.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.bn.madarsofttaskbassemnar.util.UiText
import com.bn.madarsofttaskbassemnar.R

data class InputUiState(
    val name: String = "",
    val jobTitle: String = "",
    val age: String = "",
    val gender: String = "MALE",
    val isSaved: Boolean = false,
    val error: UiText? = null
)

@HiltViewModel
class InputViewModel @Inject constructor(
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(InputUiState())
    val uiState = _uiState.asStateFlow()

    fun onNameChange(name: String) {
        _uiState.value = _uiState.value.copy(name = name)
    }

    fun onJobTitleChange(jobTitle: String) {
        _uiState.value = _uiState.value.copy(jobTitle = jobTitle)
    }

    fun onAgeChange(age: String) {
        _uiState.value = _uiState.value.copy(age = age)
    }

    fun onGenderChange(gender: String) {
        _uiState.value = _uiState.value.copy(gender = gender)
    }

    fun saveUser() {
        val state = _uiState.value
        if (state.name.isBlank() || state.jobTitle.isBlank() || state.age.isBlank()) {
            _uiState.value = _uiState.value.copy(error = UiText.StringResource(R.string.error_fill_all_fields))
            return
        }

        val ageInt = state.age.toIntOrNull()
        if (ageInt == null) {
            _uiState.value = _uiState.value.copy(error = UiText.StringResource(R.string.error_invalid_age))
            return
        }

        viewModelScope.launch {
            try {
                saveUserUseCase(
                    User(
                        name = state.name,
                        jobTitle = state.jobTitle,
                        age = ageInt,
                        gender = state.gender
                    )
                )
                _uiState.value = _uiState.value.copy(isSaved = true)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message?.let { UiText.DynamicString(it) })
            }
        }
    }

    fun resetSavedState() {
        _uiState.value = _uiState.value.copy(isSaved = false, name = "", jobTitle = "", age = "")
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}
