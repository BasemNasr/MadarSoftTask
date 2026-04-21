package com.bn.madarsofttaskbassemnar.domain.model

data class User(
    val id: Long = 0,
    val name: String,
    val jobTitle: String,
    val age: Int,
    val gender: String
)
