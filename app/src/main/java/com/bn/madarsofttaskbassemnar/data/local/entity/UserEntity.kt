package com.bn.madarsofttaskbassemnar.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bn.madarsofttaskbassemnar.domain.model.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val jobTitle: String,
    val age: Int,
    val gender: String
)

fun UserEntity.toDomain() = User(
    id = id,
    name = name,
    jobTitle = jobTitle,
    age = age,
    gender = gender
)

fun User.toEntity() = UserEntity(
    id = id,
    name = name,
    jobTitle = jobTitle,
    age = age,
    gender = gender
)
