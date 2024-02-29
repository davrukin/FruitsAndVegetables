package com.davrukin.fruitsandvegetables.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.app.ActivityCompat
import androidx.core.content.getSystemService
import com.davrukin.fruitsandvegetables.remote.NetworkClient
import com.davrukin.fruitsandvegetables.remote.NetworkClientRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

	@Provides
	fun provideNetworkClient(connectivityManager: ConnectivityManager): NetworkClient {
		return NetworkClientRetrofit(connectivityManager)
		// can swap out implementation with ktor one if desired
	}

	@Provides
	fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
		return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
	}
}