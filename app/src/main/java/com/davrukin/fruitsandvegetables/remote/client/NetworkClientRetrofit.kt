package com.davrukin.fruitsandvegetables.remote.client

import com.davrukin.fruitsandvegetables.data.ProduceItemPage
import retrofit2.Retrofit
import javax.inject.Inject

class NetworkClientRetrofit @Inject constructor(
	retrofit: Retrofit,
) : NetworkClient {

	private val service = retrofit.create(RetrofitService::class.java)

	override suspend fun getVeggies(pageNumber: Int): ProduceItemPage {
		return service.getVeggies(pageNumber - 1)
	}

	override suspend fun getFruits(pageNumber: Int): ProduceItemPage {
		return service.getFruits(pageNumber)
	}
}