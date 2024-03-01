package com.davrukin.fruitsandvegetables.remote.paging

class EndOfPagingError : Throwable(message = "No more pages to load")