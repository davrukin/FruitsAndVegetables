package com.davrukin.fruitsandvegetables.remote

import com.davrukin.fruitsandvegetables.data.ProduceItemPage

interface NetworkClient {

	suspend fun getVeggies(pageNumber: Int): ProduceItemPage

	suspend fun getFruits(pageNumber: Int): ProduceItemPage
}