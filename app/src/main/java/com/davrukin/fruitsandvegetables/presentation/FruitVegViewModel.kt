package com.davrukin.fruitsandvegetables.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.davrukin.fruitsandvegetables.data.ProduceItem
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

	val veggies: Flow<PagingData<ProduceItem>> = repository
			.getVeggies()
			.cachedIn(viewModelScope)

	// TODO: make sure initial page and total size are correct with paging code
}