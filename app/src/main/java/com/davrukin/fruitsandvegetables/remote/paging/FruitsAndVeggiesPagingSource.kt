package com.davrukin.fruitsandvegetables.remote.paging

import com.davrukin.fruitsandvegetables.data.ProduceItemPage
import com.davrukin.fruitsandvegetables.remote.client.NetworkClient
import javax.inject.Inject

class FruitsAndVeggiesPagingSource @Inject constructor(
	override val networkClient: NetworkClient,
) : BasePagingSource(networkClient) {

	override suspend fun getResponse(networkClient: NetworkClient, pageNumber: Int): ProduceItemPage {
		return networkClient.getVeggies(pageNumber = pageNumber) + networkClient.getFruits(pageNumber = pageNumber)
	}
}
