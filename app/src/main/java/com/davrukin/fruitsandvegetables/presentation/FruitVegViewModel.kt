package com.davrukin.fruitsandvegetables.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.davrukin.fruitsandvegetables.data.ProduceItem
import com.davrukin.fruitsandvegetables.data.ProduceItemType
import com.davrukin.fruitsandvegetables.remote.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FruitVegViewModel @Inject constructor(
	repository: NetworkRepository,
) : ViewModel() {

	private val _uiState = MutableStateFlow(FruitVegUIState())
	val uiState = _uiState.asStateFlow()

	fun updateLoadingState(newState: LoadingState) {
		viewModelScope.launch {
			_uiState.update {
				it.copy(currentState = newState)
			}
		}
	}

	fun updateSelectedProduceItemType(newType: ProduceItemType?) {
		viewModelScope.launch {
			_uiState.update {
				it.copy(selectedProduceItemType = newType)
			}
		}
	}

	// here in case desire to load individually
	val veggies: Flow<PagingData<ProduceItem>> = repository
		.getVeggies()
		.cachedIn(viewModelScope)

	val fruits: Flow<PagingData<ProduceItem>> = repository
		.getFruits()
		.cachedIn(viewModelScope)

	val fruitsAndVeggies: Flow<PagingData<ProduceItem>> = repository
		.getFruitsAndVeggies()
		.cachedIn(viewModelScope)

	/*
	 * as a result of the filtering, these three lists are cached in memory.
	 * there could be a performance optimization to make where "veggies" and "fruits" are
	 * combined in the screen instead of having a third redundant flow containing all of them,
	 * duplicating memory usage. I implemented filtering last after having implemented the combined query,
	 * which is why it's done this way now.
	 */

	// TODO: there seems to be a case where vertical --> horizontal --> vertical + scroll to top & back down + light/dark change causes re-paging
}