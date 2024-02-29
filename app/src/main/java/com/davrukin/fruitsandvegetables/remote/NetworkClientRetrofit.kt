package com.davrukin.fruitsandvegetables.remote

import com.davrukin.fruitsandvegetables.data.Constants
import com.davrukin.fruitsandvegetables.data.ProduceItemPage
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class NetworkClientRetrofit : NetworkClient {

	private val contentType = "application/json".toMediaType()

	private val okHttpClient = run {
		val interceptor = HttpLoggingInterceptor()
		interceptor.level = HttpLoggingInterceptor.Level.BODY

		OkHttpClient
			.Builder()
			.addInterceptor(interceptor)
			.build()
	}

	private val retrofit = Retrofit
		.Builder()
		.baseUrl(Constants.BASE_URL)
		.client(okHttpClient)
		.addConverterFactory(Json.asConverterFactory(contentType))
		.build()

	private val service = retrofit.create(RetrofitService::class.java)

	override suspend fun getVeggies(pageNumber: Int): ProduceItemPage {
		return service.getVeggies(pageNumber)
	}

	override suspend fun getFruits(pageNumber: Int): ProduceItemPage {
		return service.getFruits(pageNumber)
	}
}