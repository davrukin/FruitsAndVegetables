package com.davrukin.fruitsandvegetables.remote.paging

import androidx.annotation.StringRes
import com.davrukin.fruitsandvegetables.R

class EndOfPagingError(
	val itemCount: Int,
) : Throwable() {

	@StringRes val messageId: Int = R.string.error_end_paging

	// TODO: not technically an "error" per se, rather more informational, but having it as an error makes the code cleaner
}