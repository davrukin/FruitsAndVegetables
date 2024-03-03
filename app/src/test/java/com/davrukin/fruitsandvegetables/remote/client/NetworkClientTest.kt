package com.davrukin.fruitsandvegetables.remote.client

import com.davrukin.fruitsandvegetables.data.mockFruitPage
import com.davrukin.fruitsandvegetables.data.mockVegetablePage
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class NetworkClientTest {

	private val networkClient = mockk<NetworkClient>()

	@Test
	fun testGetVeggies() = runTest {
		coEvery {
			networkClient.getVeggies(any())
		} returns mockVegetablePage

		val veggies = networkClient.getVeggies(0)

		coVerify {
			networkClient.getVeggies(0)
		}

		confirmVerified(networkClient)

		assert(veggies.currentPage == 0)
		assert(veggies.totalPages == 4)
		assert(veggies.produceItems[0].name == "Carrot")
	}

	@Test
	fun testGetFruits() = runTest {
		coEvery {
			networkClient.getFruits(any())
		} returns mockFruitPage

		val fruits = networkClient.getFruits(0)

		coVerify {
			networkClient.getFruits(0)
		}

		confirmVerified(networkClient)

		assert(fruits.currentPage == 0)
		assert(fruits.totalPages == 4)
		assert(fruits.produceItems[0].name == "Apple")
	}
}