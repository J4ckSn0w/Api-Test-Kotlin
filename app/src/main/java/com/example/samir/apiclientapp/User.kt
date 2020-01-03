package com.example.samir.apiclientapp

data class User(
        val id: Int,
        val name: String,
        val username: String,
        val email: String,
        val address: Address,
        val geo: Geo,
        val phone: String,
        val website: String,
        val company: Company
)
