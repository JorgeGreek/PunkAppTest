package com.example.punkapitestapp.server.models

data class BeersItem(
    val description: String?,
    val id: Int?,
    val image_url: String?,
    val name: String?,
    val abv: Double?,
    val tagline: String?,
    val first_brewed: String?
)