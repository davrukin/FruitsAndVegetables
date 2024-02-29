package com.davrukin.fruitsandvegetables.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.davrukin.fruitsandvegetables.data.Constants
import com.davrukin.fruitsandvegetables.data.ProduceItem
import com.davrukin.fruitsandvegetables.remote.client.NetworkClient
import com.davrukin.fruitsandvegetables.remote.paging.VeggiesPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NetworkRepository @Inject constructor(
	private val networkClient: NetworkClient,
) {

	fun getVeggies(): Flow<PagingData<ProduceItem>> = Pager(
		config = PagingConfig(
			pageSize = Constants.PAGE_SIZE,
		),
		pagingSourceFactory = {
			VeggiesPagingSource(networkClient)
		},
	).flow
}