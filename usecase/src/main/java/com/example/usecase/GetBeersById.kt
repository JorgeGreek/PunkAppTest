package com.example.usecase

import com.example.data.repository.PunkRepository
import com.example.domain.BeersItem

class GetBeersById (private val punkRepository: PunkRepository) {
    suspend fun invoke(id: Int): List<BeersItem> = punkRepository.suspendDetails(id)
}