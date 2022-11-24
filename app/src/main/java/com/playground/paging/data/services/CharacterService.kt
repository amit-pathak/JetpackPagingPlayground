package com.playground.paging.data.services

import com.playground.paging.domain.contract.Page
import com.playground.paging.domain.response.CharactersResponse
import com.playground.paging.domain.response.Result
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("api/character?")
    suspend fun getAllCharacters(@Query("page") page: Int): CharactersResponse
}