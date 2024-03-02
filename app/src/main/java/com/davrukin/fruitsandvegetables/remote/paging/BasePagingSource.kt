package com.davrukin.fruitsandvegetables.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.davrukin.fruitsandvegetables.data.ProduceItem
import com.davrukin.fruitsandvegetables.data.ProduceItemPage
import com.davrukin.fruitsandvegetables.remote.client.NetworkClient
import com.davrukin.fruitsandvegetables.remote.networkMonitor.NoNetworkException

abstract class BasePagingSource(
	open val networkClient: NetworkClient,
) : PagingSource<Int, ProduceItem>() {

	private var currentPage = 0
	private var totalPages = 0
	private var itemCount = 0

	abstract suspend fun getResponse(networkClient: NetworkClient, pageNumber: Int): ProduceItemPage

	override fun getRefreshKey(state: PagingState<Int, ProduceItem>): Int? {
		return state
			.anchorPosition
			?.let { anchorPosition ->
				state
					.closestPageToPosition(anchorPosition)
					?.prevKey
					?.plus(1)
					?: state
						.closestPageToPosition(anchorPosition)
						?.nextKey
						?.minus(1)
			}
	}

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProduceItem> {
		return try {
			val page = params.key ?: 0 // Start from page 0
			val response = getResponse(networkClient, page)
			currentPage = response.currentPage
			totalPages = response.totalPages
			itemCount += response.produceItems.size

			LoadResult.Page(
				data = response.produceItems,
				prevKey = if (page == 0) null else page - 1, // Adjust prevKey calculation
				nextKey = if (response.produceItems.isEmpty()) null else page + 1 // Stop loading after last page
			)
		} catch (e: Exception) {
			if (e !is NoNetworkException && (currentPage + 1) >= totalPages) {
				LoadResult.Error(EndOfPagingError(itemCount))
			} else {
				LoadResult.Error(e)
			}
		}
	}
}