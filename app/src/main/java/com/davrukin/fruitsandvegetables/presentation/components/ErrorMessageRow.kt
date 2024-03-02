package com.davrukin.fruitsandvegetables.presentation.components

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import com.davrukin.fruitsandvegetables.remote.networkMonitor.NoNetworkException
import com.davrukin.fruitsandvegetables.remote.paging.EndOfPagingError
import com.davrukin.fruitsandvegetables.ui.theme.FruitsAndVegetablesTheme
import com.davrukin.fruitsandvegetables.utils.extensions.getErrorMessage

@Composable
fun ErrorMessageRow(state: LoadState.Error) {
	val message = when (val error = state.error) {
		is NoNetworkException -> stringResource(id = error.messageId)
		is EndOfPagingError -> error.getErrorMessage()
		else -> state.error.message.toString()
	}

	MessageRow(message = message)
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ErrorMessageRowPreview() {
	FruitsAndVegetablesTheme {
		ErrorMessageRow(
			state = LoadState.Error(Throwable(message = "Compose Preview Error")),
		)
	}
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ErrorMessageRowPreviewNoNetworkException() {
	FruitsAndVegetablesTheme {
		ErrorMessageRow(
			state = LoadState.Error(NoNetworkException()),
		)
	}
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ErrorMessageRowPreviewEndOfPagingError() {
	FruitsAndVegetablesTheme {
		ErrorMessageRow(
			state = LoadState.Error(EndOfPagingError(60)),
		)
	}
}