package com.davrukin.fruitsandvegetables.remote.paging

class EndOfPagingError(pageCount: Int) : Throwable(message = "No more pages to load; Total items loaded = $pageCount")