package com.davrukin.fruitsandvegetables.remote.networkMonitor

import android.net.ConnectivityManager

class LiveNetworkMonitor(
	private val connectivityManager: ConnectivityManager,
) : NetworkMonitor {
	override fun isConnected(): Boolean {
		return connectivityManager.activeNetwork != null
	}
}