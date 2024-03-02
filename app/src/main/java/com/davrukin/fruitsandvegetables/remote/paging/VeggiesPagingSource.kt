package com.davrukin.fruitsandvegetables.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.davrukin.fruitsandvegetables.data.ProduceItem
import com.davrukin.fruitsandvegetables.data.ProduceItemPage
import com.davrukin.fruitsandvegetables.remote.client.NetworkClient
import javax.inject.Inject

class VeggiesPagingSource @Inject constructor(
	override val networkClient: NetworkClient,
) : BasePagingSource(networkClient) {

	override suspend fun getResponse(networkClient: NetworkClient, pageNumber: Int): ProduceItemPage {
		return networkClient.getVeggies(pageNumber = pageNumber)
	}
}
