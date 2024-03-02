package com.davrukin.fruitsandvegetables.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davrukin.fruitsandvegetables.R
import com.davrukin.fruitsandvegetables.data.ProduceItemType
import com.davrukin.fruitsandvegetables.ui.theme.FruitsAndVegetablesTheme

/**
 * Header of Lazy Column which lets user select between fruits, vegetables, or both
 *
 * @param modifier the [Modifier] to apply
 * @param onSelectFilter callback to [com.davrukin.fruitsandvegetables.presentation.FruitVegScreen]; null is both selected
 */
@Composable
fun FilterHeader(
	modifier: Modifier = Modifier,
	onSelectFilter: (ProduceItemType?) -> Unit,
) {
	var selectedProduceType by rememberSaveable {
		mutableStateOf<ProduceItemType?>(null)
	}

	Row(
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.SpaceEvenly,
		modifier = modifier
			.fillMaxWidth()
			.background(Color.LightGray)
			.padding(12.dp)
			.clip(RoundedCornerShape(8.dp)),
		content = {
			Text(
				text = stringResource(id = R.string.filter_fruits),
				fontFamily = FontFamily.Monospace,
				fontWeight = if (selectedProduceType == ProduceItemType.FRUIT) {
					FontWeight.Bold
				} else {
					FontWeight.Normal
				},
				modifier = Modifier
					.padding(4.dp)
					.clickable {
						selectedProduceType = ProduceItemType.FRUIT
						onSelectFilter.invoke(ProduceItemType.FRUIT)
					},
			)
			Text(
				text = stringResource(id = R.string.filter_both),
				fontFamily = FontFamily.Monospace,
				fontWeight = if (selectedProduceType == null) {
					FontWeight.Bold
				} else {
					FontWeight.Normal
				},
				modifier = Modifier
					.padding(4.dp)
					.clickable {
						selectedProduceType = null
						onSelectFilter.invoke(null)
					},
			)
			Text(
				text = stringResource(id = R.string.filter_veggies),
				fontFamily = FontFamily.Monospace,
				fontWeight = if (selectedProduceType == ProduceItemType.VEGETABLE) {
					FontWeight.Bold
				} else {
					FontWeight.Normal
				},
				modifier = Modifier
					.padding(4.dp)
					.clickable {
						selectedProduceType = ProduceItemType.VEGETABLE
						onSelectFilter.invoke(ProduceItemType.VEGETABLE)
					},
			)
		},
	)
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun FilterHeaderPreview() {
	FruitsAndVegetablesTheme {
		FilterHeader(
			onSelectFilter = { null },
		)
	}
}