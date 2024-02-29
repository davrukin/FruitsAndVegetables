package com.davrukin.fruitsandvegetables.remote.client

import com.davrukin.fruitsandvegetables.data.Constants
import com.davrukin.fruitsandvegetables.data.ProduceItemPage
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

	@GET(Constants.GET_VEGGIES)
	suspend fun getVeggies(
		@Path("pageNumber") pageNumber: Int,
	): ProduceItemPage

	@GET(Constants.GET_FRUITS)
	suspend fun getFruits(
		@Path("pageNumber") pageNumber: Int,
	): ProduceItemPage
}