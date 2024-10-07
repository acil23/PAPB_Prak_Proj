package com.papb.lk2praktikum

data class GithubUserResponse(
    val login: String,         // Username
    val name: String?,         // Nama
    val avatar_url: String,    // URL Foto Profil
    val followers: Int,        // Jumlah Followers
    val following: Int         // Jumlah Following
)
