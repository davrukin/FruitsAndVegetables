package com.davrukin.fruitsandvegetables.remote.client

import com.davrukin.fruitsandvegetables.data.Constants
import com.davrukin.fruitsandvegetables.data.ProduceItemPage
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.http.URLBuilder
import javax.inject.Inject

class NetworkClientKtor @Inject constructor() : NetworkClient {

	// TODO: implement later

	override suspend fun getVeggies(pageNumber: Int): ProduceItemPage {
		val client = HttpClient(CIO)
		val response = client.get(url)
		return response.body()
	}

	val url = URLBuilder(
		pathSegments = listOf(
			Constants.BASE_URL,
			Constants.GET_VEGGIES,
		),
	).build()

	override suspend fun getFruits(pageNumber: Int): ProduceItemPage {
		TODO("Not yet implemented")
	}
}