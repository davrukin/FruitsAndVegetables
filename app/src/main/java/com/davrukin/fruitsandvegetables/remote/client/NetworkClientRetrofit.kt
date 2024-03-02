package com.davrukin.fruitsandvegetables.remote.client

import com.davrukin.fruitsandvegetables.data.ProduceItemPage
import com.davrukin.fruitsandvegetables.data.ProduceItemType
import retrofit2.Retrofit
import javax.inject.Inject

class NetworkClientRetrofit @Inject constructor(
	retrofit: Retrofit,
) : NetworkClient {

	private val service = retrofit.create(RetrofitService::class.java)

	override suspend fun getVeggies(pageNumber: Int): ProduceItemPage {
		return service.getVeggies(pageNumber)
	}

	override suspend fun getFruits(pageNumber: Int): ProduceItemPage {
		val fruits = service.getFruits(pageNumber)
		return fruits.copy(produceItems = fruits.produceItems.map { it.copy(type = ProduceItemType.FRUIT) }) // there might be a better way to do this
	}
}
