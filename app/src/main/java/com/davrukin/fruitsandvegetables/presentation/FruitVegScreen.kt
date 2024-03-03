package com.davrukin.fruitsandvegetables.presentation

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.davrukin.fruitsandvegetables.R
import com.davrukin.fruitsandvegetables.data.ProduceItemType
import com.davrukin.fruitsandvegetables.presentation.components.ErrorMessageRow
import com.davrukin.fruitsandvegetables.presentation.components.FilterHeader
import com.davrukin.fruitsandvegetables.presentation.components.InitialLoadProgressIndicator
import com.davrukin.fruitsandvegetables.presentation.components.ProduceItemRow
import com.davrukin.fruitsandvegetables.remote.paging.EndOfPagingError
import com.davrukin.fruitsandvegetables.ui.theme.FruitsAndVegetablesTheme

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun FruitVegScreen(
	modifier: Modifier = Modifier,
	viewModel: FruitVegViewModel = hiltViewModel(),
) {
	val uiState by viewModel.uiState.collectAsStateWithLifecycle()

	val fruitsAndVeggies = when (uiState.selectedProduceItemType) {
		ProduceItemType.FRUIT -> viewModel.fruits.collectAsLazyPagingItems()
		ProduceItemType.VEGETABLE -> viewModel.veggies.collectAsLazyPagingItems()
		null -> viewModel.fruitsAndVeggies.collectAsLazyPagingItems()
	}

	val context = LocalContext.current

	val pullToRefreshState = rememberPullRefreshState(
		refreshing = uiState.currentState == LoadingState.LOADING,
		onRefresh = fruitsAndVeggies::refresh,
	)

	Box(modifier.pullRefresh(pullToRefreshState)) {
		LazyColumn(
			modifier = Modifier,
			content = {
				stickyHeader {
					FilterHeader(
						onSelectFilter = viewModel::updateSelectedProduceItemType,
					)
				}
				items(
					count = fruitsAndVeggies.itemCount,
					key = fruitsAndVeggies.itemKey(),
					contentType = fruitsAndVeggies.itemContentType(),
					itemContent = { index ->
						fruitsAndVeggies[index]?.let {
							ProduceItemRow(produceItem = it)
						}
					}
				)

				when (val state = fruitsAndVeggies.loadState.refresh) { // first load
					is LoadState.Error -> {
						viewModel.updateLoadingState(LoadingState.ERROR)

						item {
							ErrorMessageRow(state)
						}
					}

					is LoadState.Loading -> {
						viewModel.updateLoadingState(LoadingState.LOADING)

						item {
							InitialLoadProgressIndicator()
						}
					}

					else -> viewModel.updateLoadingState(LoadingState.DONE)
				}

				when (val state = fruitsAndVeggies.loadState.append) { // pagination
					is LoadState.Error -> {
						viewModel.updateLoadingState(LoadingState.ERROR)

						item {
							ErrorMessageRow(state)
						}

						if (state.error is EndOfPagingError) {
							Toast.makeText(context, context.getString(R.string.success_all_loaded, fruitsAndVeggies.itemCount), Toast.LENGTH_LONG).show()
							// this shows frequently every time loading is completed, rather than just once.
							// a limit could possibly be added if required or requested.
						}
					}

					// pagination loading ui
					is LoadState.Loading -> viewModel.updateLoadingState(LoadingState.LOADING)

					else -> viewModel.updateLoadingState(LoadingState.DONE)
				}
			},
		)

		PullRefreshIndicator(
			refreshing = uiState.currentState == LoadingState.LOADING,
			state = pullToRefreshState,
			modifier = Modifier.align(Alignment.TopCenter),
		)
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