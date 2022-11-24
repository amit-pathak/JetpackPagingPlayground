package com.playground.paging.domain.usecase

import androidx.paging.PagingData
import com.playground.paging.domain.repositories.CharacterRepository
import com.playground.paging.domain.response.Result
import com.playground.paging.domain.sources.createPager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(private val repository: CharacterRepository) {

    fun get(): Flow<PagingData<Result>> {
        return createPager { repository.getAllCharacters(it) }.flow
    }

}
