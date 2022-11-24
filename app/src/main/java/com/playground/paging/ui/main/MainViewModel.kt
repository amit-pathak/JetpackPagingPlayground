package com.playground.paging.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.playground.paging.domain.response.Result
import com.playground.paging.domain.usecase.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getCharacterUseCase: GetCharacterUseCase): ViewModel() {

    fun load(): Flow<PagingData<Result>> {
        return getCharacterUseCase.get().cachedIn(viewModelScope)
    }
}