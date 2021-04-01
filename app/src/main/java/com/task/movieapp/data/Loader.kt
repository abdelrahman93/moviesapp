package com.task.movieapp.data

import com.task.movieapp.BaseViewState

sealed class Loader : BaseViewState() {
    data class Shimmer(var show: Boolean) : Loader()
    data class Progress(var show: Boolean) : Loader()
}


