package com.davrukin.fruitsandvegetables.remote.networkMonitor

import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class NetworkMonitorTest {

	private val networkMonitor = mockk<NetworkMonitor>()

	@Test
	fun testIsConnected() {
		every {
			networkMonitor.isConnected()
		} returns true

		val isConnected = networkMonitor.isConnected()

		verify {
			networkMonitor.isConnected()
		}

		confirmVerified(networkMonitor)

		assert(isConnected)
	}
}