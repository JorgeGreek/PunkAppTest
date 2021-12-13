package com.example.usecase

import com.example.data.repository.PunkRepository
import com.example.domain.BeersItem

class GetBeers (private val punkRepository: PunkRepository) {
    suspend fun invoke(page: Int): List<BeersItem> = punkRepository.suspendBeers(page)
}