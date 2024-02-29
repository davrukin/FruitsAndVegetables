package com.davrukin.fruitsandvegetables.presentation

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.davrukin.fruitsandvegetables.ui.theme.FruitsAndVegetablesTheme

@Composable
fun FruitVegScreen(
	modifier: Modifier = Modifier,
) {

}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun FruitVegScreenPreview() {
	FruitsAndVegetablesTheme {
		FruitVegScreen()
	}
}