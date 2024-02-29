package com.davrukin.fruitsandvegetables.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.davrukin.fruitsandvegetables.ui.theme.FruitsAndVegetablesTheme
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun FruitVegScreen(
	modifier: Modifier = Modifier,
	viewModel: FruitVegViewModel = hiltViewModel(),
) {
	val veggies = viewModel.veggies.collectAsLazyPagingItems()

	// TODO: add pull to refresh

	LazyColumn(
		modifier = modifier,
	) {
		items(
			count = veggies.itemCount,
			key = veggies.itemKey(),
			contentType = veggies.itemContentType()
		) { index ->
			val veg = veggies[index]
			
			Text(
				text = "Name: ${veg?.name}",
			)

			HorizontalDivider()
		}

		when (val state = veggies.loadState.refresh) { // first load
			is LoadState.Error -> {
				// TODO: error item
				// state.error to get error message
				item {
					Text(state.error.message.toString())
				}
				// TODO: http 404 is last item, but index 4, shouldn't do that anyway
			}
			is LoadState.Loading -> {
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
			} else -> {}
		}

		when (val state = veggies.loadState.append) { // pagination
			is LoadState.Error -> {
				// TODO: pagination Error Item
				// state.error to get error message
				item {
					Text(state.error.message.toString())
				}
			}
			is LoadState.Loading -> { // pagination loading ui
				item {
					Column(
						modifier = Modifier
							.fillMaxWidth(),
						horizontalAlignment = Alignment.CenterHorizontally,
						verticalArrangement = Arrangement.Center,
					) {
						Text(text = "Pagination Loading")

						CircularProgressIndicator(color = Color.Black)
					}
				}
			}
			else -> {}
		}
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