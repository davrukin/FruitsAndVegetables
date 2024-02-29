package com.davrukin.fruitsandvegetables.remote

import android.net.ConnectivityManager
import com.davrukin.fruitsandvegetables.data.Constants
import com.davrukin.fruitsandvegetables.data.ProduceItemPage
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class NetworkClientRetrofit(
	private val connectivityManager: ConnectivityManager,
) : NetworkClient {

	private val contentType = "application/json".toMediaType()

	// TODO: inject

	private val okHttpClient = run {
		val loggingInterceptor = HttpLoggingInterceptor()
		loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

		val networkInterceptor = NetworkMonitorInterceptor(connectivityManager)

		OkHttpClient
			.Builder()
			.addInterceptor(loggingInterceptor)
			.addInterceptor(networkInterceptor)
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