package com.davrukin.fruitsandvegetables.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davrukin.fruitsandvegetables.R
import com.davrukin.fruitsandvegetables.ui.theme.FruitsAndVegetablesTheme

@Composable
fun LazyItemScope.InitialLoadProgressIndicator() {
	Column(
		modifier = Modifier
			.fillParentMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center,
	) {
		Text(
			modifier = Modifier
				.padding(8.dp),
			text = stringResource(id = R.string.loading),
		)

		CircularProgressIndicator(color = Color.Black)
	}
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun InitialLoadProgressIndicatorPreview() {
	FruitsAndVegetablesTheme {
		LazyColumn {
			item {
				InitialLoadProgressIndicator()
			}
		}
	}
}