package com.davrukin.fruitsandvegetables.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.io.Serial

@Serializable
data class ProduceItemPage(
	val currentPage: Int,
	val totalPages: Int,
	@SerialName("feed") val produceItems: List<ProduceItem>,
)
