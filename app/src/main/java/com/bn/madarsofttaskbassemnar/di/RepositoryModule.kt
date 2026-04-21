package com.bn.madarsofttaskbassemnar.di

import com.bn.madarsofttaskbassemnar.data.repository.UserRepositoryImpl
import com.bn.madarsofttaskbassemnar.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}
