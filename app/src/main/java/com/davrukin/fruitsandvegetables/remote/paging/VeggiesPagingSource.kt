package com.davrukin.fruitsandvegetables.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.davrukin.fruitsandvegetables.data.ProduceItem
import com.davrukin.fruitsandvegetables.data.ProduceItemPage
import com.davrukin.fruitsandvegetables.remote.NetworkClient
import com.davrukin.fruitsandvegetables.remote.RetrofitService

class VeggiesPagingSource(
	private val networkClient: NetworkClient,
) : PagingSource<Int, ProduceItem>() {
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
			val page = params.key ?: 1
			val response = networkClient.getVeggies(pageNumber = page)

			LoadResult.Page(
				data = response.produceItems,
				prevKey = if (page == 1) null else page.minus(1),
				nextKey = if (response.produceItems.isEmpty()) null else page.plus(1),
			)
		} catch (e: Exception) {
			LoadResult.Error(e)
		}
	}
}
