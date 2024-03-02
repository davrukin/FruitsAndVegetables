package com.davrukin.fruitsandvegetables.presentation

import androidx.compose.runtime.Immutable
import com.davrukin.fruitsandvegetables.data.ProduceItemType

@Immutable
data class FruitVegUIState(
	val currentState: LoadingState = LoadingState.DONE,
	val selectedProduceItemType: ProduceItemType? = null,
)
