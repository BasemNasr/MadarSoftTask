package com.bn.madarsofttaskbassemnar.presentation.display

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bn.madarsofttaskbassemnar.domain.model.User
import com.bn.madarsofttaskbassemnar.domain.usecase.DeleteUserUseCase
import com.bn.madarsofttaskbassemnar.domain.usecase.GetAllUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DisplayViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
) : ViewModel() {

    val users: StateFlow<List<User>> = getAllUsersUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun deleteUser(user: User) {
        viewModelScope.launch {
            deleteUserUseCase(user)
        }
    }
}
