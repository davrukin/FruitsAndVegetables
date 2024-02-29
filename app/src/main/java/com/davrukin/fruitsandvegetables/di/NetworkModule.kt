package com.davrukin.fruitsandvegetables.di

import android.content.Context
import android.net.ConnectivityManager
import com.davrukin.fruitsandvegetables.data.Constants
import com.davrukin.fruitsandvegetables.remote.client.NetworkClient
import com.davrukin.fruitsandvegetables.remote.client.NetworkClientRetrofit
import com.davrukin.fruitsandvegetables.remote.networkMonitor.LiveNetworkMonitor
import com.davrukin.fruitsandvegetables.remote.networkMonitor.NetworkMonitor
import com.davrukin.fruitsandvegetables.remote.networkMonitor.NetworkMonitorInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

	@Binds
	@Singleton
	abstract fun provideNetworkClient(client: NetworkClientRetrofit): NetworkClient
	// can swap out implementation with ktor one if desired

	companion object {
		@Provides
		fun provideNetworkMonitor(connectivityManager: ConnectivityManager): NetworkMonitor {
			return LiveNetworkMonitor(connectivityManager)
		}

		@Provides
		fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
			return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
		}

		@Provides
		@Singleton
		fun provideOkHttpClient(networkMonitor: NetworkMonitor): OkHttpClient {
			val loggingInterceptor = HttpLoggingInterceptor()
			loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

			val networkInterceptor = NetworkMonitorInterceptor(networkMonitor)

			return OkHttpClient
				.Builder()
				.addInterceptor(loggingInterceptor)
				.addInterceptor(networkInterceptor)
				.build()
		}

		@Provides
		@Singleton
		fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
			val contentType = "application/json".toMediaType()

			return Retrofit
				.Builder()
				.baseUrl(Constants.BASE_URL)
				.client(okHttpClient)
				.addConverterFactory(Json.asConverterFactory(contentType))
				.build()
		}
	}
}