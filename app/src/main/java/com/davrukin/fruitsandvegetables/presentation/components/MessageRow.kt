package com.davrukin.fruitsandvegetables.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davrukin.fruitsandvegetables.ui.theme.FruitsAndVegetablesTheme

@Composable
fun MessageRow(
	modifier: Modifier = Modifier,
	message: String,
) {
	Column(
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.Start,
		modifier = modifier
			.fillMaxWidth()
			.padding(12.dp)
			.clip(RoundedCornerShape(8.dp))
			.background(Color.LightGray),
		content = {
			Text(
				text = message,
				fontFamily = FontFamily.Monospace,
				modifier = Modifier.padding(4.dp),
			)
		},
	)
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun MessageRowPreview() {
	FruitsAndVegetablesTheme {
		MessageRow(
			message = "No Internet",
		)
	}
}