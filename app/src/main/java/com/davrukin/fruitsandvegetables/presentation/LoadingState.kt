package com.davrukin.fruitsandvegetables.presentation

/**
 * Custom error states to be separate from ones used by Paging ([androidx.paging.LoadState]) for separation of concerns
 */
enum class LoadingState {
	ERROR,
	LOADING,
	DONE,
}