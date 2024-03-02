package com.davrukin.fruitsandvegetables.remote.paging

import androidx.annotation.StringRes
import com.davrukin.fruitsandvegetables.R

class EndOfPagingError(
	val itemCount: Int,
) : Throwable() {

	@StringRes val messageId: Int = R.string.error_end_paging
}