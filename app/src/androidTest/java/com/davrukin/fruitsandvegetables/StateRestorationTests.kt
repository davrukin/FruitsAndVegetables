package com.davrukin.fruitsandvegetables

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.hilt.navigation.compose.hiltViewModel
import com.davrukin.fruitsandvegetables.data.ProduceItemType
import com.davrukin.fruitsandvegetables.presentation.FruitVegScreen
import com.davrukin.fruitsandvegetables.presentation.FruitVegViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class StateRestorationTests {

	@get:Rule
	val hiltRule = HiltAndroidRule(this)

	@get:Rule
	val composeTestRule = createAndroidComposeRule<TestMainActivity>()

	@Before
	fun init() {
		hiltRule.inject()
	}

	@Test
	fun onRecreation_stateIsRestored() {
		val restorationTester = StateRestorationTester(composeTestRule)

		restorationTester.setContent { FruitVegScreen() }

		var viewModel: FruitVegViewModel? = null

		composeTestRule.activity.runOnUiThread {
			composeTestRule.activity.setContent {
				viewModel = hiltViewModel()
				viewModel?.let {
					FruitVegScreen(viewModel = it)
				}
			}
		}

		// TODO: run actions which modify the state
		composeTestRule.onNodeWithText("Fruits").performClick()

		// Trigger a recreation
		restorationTester.emulateSavedInstanceStateRestore()

		// TODO: verify that state has been correctly restored
		assert(viewModel?.uiState?.value?.selectedProduceItemType == ProduceItemType.FRUIT)

		composeTestRule.onNodeWithText("Veggies").performClick() // TODO: test tag instead of by string
		restorationTester.emulateSavedInstanceStateRestore()
		assert(viewModel?.uiState?.value?.selectedProduceItemType == ProduceItemType.VEGETABLE)

		composeTestRule.onNodeWithText("Both").performClick()
		restorationTester.emulateSavedInstanceStateRestore()
		assert(viewModel?.uiState?.value?.selectedProduceItemType == null)
	}
}