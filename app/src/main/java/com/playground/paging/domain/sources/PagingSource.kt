package com.playground.paging.domain.sources

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.playground.paging.domain.contract.Page
import com.playground.paging.domain.response.PageItem

class BasePagingSource<T : PageItem>(
    private val block: suspend (Int) -> Page<T>
) : PagingSource<Int, T>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = block(page)
            LoadResult.Page(
                data = response.getContent(),
                prevKey = if (page == STARTING_PAGE_INDEX) null else  page.minus(1),
                nextKey = if (response.isLast()) null else page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 0
    }
}

private const val DEFAULT_PAGE_SIZE = 20

fun <T : PageItem> createPager(
    pageSize: Int = DEFAULT_PAGE_SIZE,
    enablePlaceholders: Boolean = false,
    block: suspend (Int) -> Page<T>
): Pager<Int, T> = Pager(
    config = PagingConfig(enablePlaceholders = enablePlaceholders, pageSize = pageSize),
    pagingSourceFactory = { BasePagingSource(block) }
)
