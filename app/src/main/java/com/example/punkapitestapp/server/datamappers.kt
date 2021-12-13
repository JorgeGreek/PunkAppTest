package com.example.punkapitestapp.server


import com.example.punkapitestapp.server.models.BeersItem as ServerBeersItem
import com.example.domain.BeersItem as DomainBeersItem


fun ServerBeersItem.toDomainBeerItem(): DomainBeersItem = DomainBeersItem(
    description,
    id,
    image_url,
    name,
    abv,
    tagline,
    first_brewed
)
