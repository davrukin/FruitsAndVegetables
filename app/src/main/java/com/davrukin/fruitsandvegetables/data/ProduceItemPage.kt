package com.davrukin.fruitsandvegetables.data

import androidx.compose.runtime.Immutable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Immutable
data class ProduceItemPage(
	val currentPage: Int,
	val totalPages: Int,
	@SerialName("feed") val produceItems: List<ProduceItem>,
) {

	/**
	 * Adds two [ProduceItemPage]s together. If the [currentPage] and [totalPages] are not equal (not from the same page), then returns the receiver's instance
	 *
	 * @param other another [ProduceItemPage] to add to this one
	 * @return a new ProduceItemPage with the two [produceItems] added together
	 */
	operator fun plus(other: ProduceItemPage): ProduceItemPage {
		return if (currentPage == other.currentPage && totalPages == other.totalPages) {
			ProduceItemPage(
				currentPage = currentPage,
				totalPages = totalPages,
				produceItems = produceItems + other.produceItems,
			)
		} else {
			this
		}
	}
}
