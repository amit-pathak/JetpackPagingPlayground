package com.playground.paging.data.repositories

import com.playground.paging.data.services.CharacterService
import com.playground.paging.domain.contract.Page
import com.playground.paging.domain.repositories.CharacterRepository
import com.playground.paging.domain.response.CharactersResponse
import com.playground.paging.domain.response.Result
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(private val service: CharacterService) :
    CharacterRepository {

    override suspend fun getAllCharacters(pageNumber: Int): Page<Result> {
        return service.getAllCharacters(pageNumber)
    }
}