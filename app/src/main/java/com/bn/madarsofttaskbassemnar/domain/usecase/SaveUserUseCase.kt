package com.bn.madarsofttaskbassemnar.domain.usecase

import com.bn.madarsofttaskbassemnar.domain.model.User
import com.bn.madarsofttaskbassemnar.domain.repository.UserRepository
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User) {
        repository.saveUser(user)
    }
}
