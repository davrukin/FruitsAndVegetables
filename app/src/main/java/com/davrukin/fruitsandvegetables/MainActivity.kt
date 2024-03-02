package com.davrukin.fruitsandvegetables

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.davrukin.fruitsandvegetables.presentation.FruitVegScreen
import com.davrukin.fruitsandvegetables.ui.theme.FruitsAndVegetablesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	@OptIn(ExperimentalMaterial3Api::class)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			FruitsAndVegetablesTheme {
				Scaffold(
					modifier = Modifier.fillMaxSize(),
					topBar = {
						 TopAppBar(
							 title = {
								 Text(
									 text = "Fruits and Vegetables",
									 fontFamily = FontFamily.Monospace,
								 )
							 },
							 colors = TopAppBarDefaults.topAppBarColors(
								 containerColor = MaterialTheme.colorScheme.primaryContainer,
								 titleContentColor = MaterialTheme.colorScheme.primary,
							 ),
						 )
					},
					content = { innerPadding ->
						FruitVegScreen(
							modifier = Modifier.padding(innerPadding)
						)
					},
				)
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	FruitsAndVegetablesTheme {
		FruitVegScreen()
	}
}