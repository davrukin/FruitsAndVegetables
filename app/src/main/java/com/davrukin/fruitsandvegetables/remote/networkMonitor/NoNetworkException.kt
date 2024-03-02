package com.davrukin.fruitsandvegetables.remote.networkMonitor

import androidx.annotation.StringRes
import com.davrukin.fruitsandvegetables.R
import java.io.IOException

class NoNetworkException : IOException() {
	@StringRes val messageId: Int = R.string.error_no_network
}