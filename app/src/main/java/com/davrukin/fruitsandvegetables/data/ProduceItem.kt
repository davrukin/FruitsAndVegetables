package com.davrukin.fruitsandvegetables.data

import androidx.compose.runtime.Immutable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Immutable
data class ProduceItem(
	val name: String,
	@SerialName("full_name") val fullName: String,
	val calories: String,
	val type: ProduceItemType = ProduceItemType.VEGETABLE,
)
