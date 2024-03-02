package com.davrukin.fruitsandvegetables.utils.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.davrukin.fruitsandvegetables.remote.paging.EndOfPagingError

@Composable
fun EndOfPagingError.getErrorMessage(): String {
	return stringResource(id = messageId, itemCount)
	// outside of definition of custom exception class to separate concerns
}