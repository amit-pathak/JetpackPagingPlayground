package com.playground.paging.domain.repositories

import com.playground.paging.domain.contract.Page
import com.playground.paging.domain.response.CharactersResponse
import com.playground.paging.domain.response.Result

interface CharacterRepository {

    suspend fun getAllCharacters(pageNumber: Int): Page<Result>
}