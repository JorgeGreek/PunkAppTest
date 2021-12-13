package com.example.usecase

import com.example.data.repository.PunkRepository
import com.example.domain.BeersItem

class GetBeersByName (private val punkRepository: PunkRepository) {
    suspend fun invoke(name: String, page: Int): List<BeersItem> = punkRepository.suspendSearchBeers(name, page)
}