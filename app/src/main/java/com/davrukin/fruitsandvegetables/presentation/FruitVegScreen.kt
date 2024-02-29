package com.davrukin.fruitsandvegetables.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.davrukin.fruitsandvegetables.presentation.components.MessageRow
import com.davrukin.fruitsandvegetables.presentation.components.ProduceItemRow
import com.davrukin.fruitsandvegetables.ui.theme.FruitsAndVegetablesTheme
import dagger.hilt.android.lifecycle.HiltViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FruitVegScreen(
	modifier: Modifier = Modifier,
	viewModel: FruitVegViewModel = hiltViewModel(),
) {
	val uiState by viewModel.uiState.collectAsStateWithLifecycle()

	val veggies = viewModel.veggies.collectAsLazyPagingItems()

	val pullToRefreshState = rememberPullRefreshState(
		refreshing = uiState.currentState == LoadingState.LOADING,
		onRefresh = veggies::refresh,
	)

	Box(modifier.pullRefresh(pullToRefreshState)) {
		LazyColumn(
			modifier = Modifier,
		) {
			items(
				count = veggies.itemCount,
				key = veggies.itemKey(),
				contentType = veggies.itemContentType()
			) { index ->
				veggies[index]?.let {
					ProduceItemRow(produceItem = it)
				}
			}

			when (val state = veggies.loadState.refresh) { // first load
				is LoadState.Error -> {
					viewModel.updateLoadingState(LoadingState.ERROR)

					// TODO: error item
					// state.error to get error message
					item {
						MessageRow(message = state.error.message.toString())
					}
					// TODO: http 404 is last item, but index 4, shouldn't do that anyway
				}

				is LoadState.Loading -> {
					viewModel.updateLoadingState(LoadingState.LOADING)

					item {
						Column(
							modifier = Modifier
								.fillParentMaxSize(),
							horizontalAlignment = Alignment.CenterHorizontally,
							verticalArrangement = Arrangement.Center,
						) {
							Text(
								modifier = Modifier
									.padding(8.dp),
								text = "Refresh Loading"
							)

							CircularProgressIndicator(color = Color.Black)
						}
					}
				}

				else -> {
					viewModel.updateLoadingState(LoadingState.DONE)
				}
			}

			when (val state = veggies.loadState.append) { // pagination
				is LoadState.Error -> {
					viewModel.updateLoadingState(LoadingState.ERROR)

					// TODO: pagination Error Item
					// state.error to get error message
					item {
						MessageRow(message = state.error.message.toString())
					}
				}

				is LoadState.Loading -> { // pagination loading ui
					viewModel.updateLoadingState(LoadingState.LOADING)

					item {
						Column(
							modifier = Modifier
								.fillParentMaxSize(),
							horizontalAlignment = Alignment.CenterHorizontally,
							verticalArrangement = Arrangement.Center,
						) {
							MessageRow(message = "Pagination Loading")

							CircularProgressIndicator(color = Color.Black)
						}
					}
				}

				else -> {
					viewModel.updateLoadingState(LoadingState.DONE)
				}
			}
		}

		PullRefreshIndicator(uiState.currentState == LoadingState.LOADING, pullToRefreshState, Modifier.align(Alignment.TopCenter))
	}
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun FruitVegScreenPreview() {
	FruitsAndVegetablesTheme {
		FruitVegScreen()
	}
}