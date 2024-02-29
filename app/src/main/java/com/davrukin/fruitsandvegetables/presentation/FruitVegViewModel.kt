package com.davrukin.fruitsandvegetables.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.davrukin.fruitsandvegetables.data.ProduceItem
import com.davrukin.fruitsandvegetables.remote.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FruitVegViewModel @Inject constructor(
	repository: NetworkRepository,
) : ViewModel() {

	val veggies: Flow<PagingData<ProduceItem>> = repository
			.getVeggies()
			.cachedIn(viewModelScope)

	// TODO: make sure initial page and total size are correct with paging code
}