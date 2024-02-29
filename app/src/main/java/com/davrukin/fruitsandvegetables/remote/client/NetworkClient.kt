package com.davrukin.fruitsandvegetables.remote.client

import com.davrukin.fruitsandvegetables.data.ProduceItemPage

interface NetworkClient {

	suspend fun getVeggies(pageNumber: Int): ProduceItemPage

	suspend fun getFruits(pageNumber: Int): ProduceItemPage
}