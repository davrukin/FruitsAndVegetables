package com.davrukin.fruitsandvegetables.presentation

import com.davrukin.fruitsandvegetables.data.ProduceItemType

data class FruitVegUIState(
	val currentState: LoadingState = LoadingState.DONE,
	val selectedProduceItemType: ProduceItemType? = null,
)
