package com.davrukin.fruitsandvegetables.remote

import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException
import javax.inject.Inject

class NetworkMonitorInterceptor(
	private val connectivityManager: ConnectivityManager, // TODO: fix concrete
) : Interceptor {
	override fun intercept(chain: Interceptor.Chain): Response {
		val request = chain.request()

		if (connectivityManager.activeNetwork != null) {
			return chain.proceed(request)
		} else {
			throw IOException("No network")
		}
	}
}