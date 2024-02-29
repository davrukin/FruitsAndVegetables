package com.davrukin.fruitsandvegetables.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.io.Serial

@Serializable
data class ProduceItem(
	val name: String,
	@SerialName("full_name") val fullName: String,
	val calories: String,
	val type: ProduceItemType = ProduceItemType.VEGETABLE,
)
