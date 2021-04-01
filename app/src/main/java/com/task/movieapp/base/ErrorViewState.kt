package com.task.movieapp.base

import com.task.movieapp.BaseViewState

sealed class ErrorViewState : BaseViewState() {
    data class Error(val message: String?) : BaseViewState()
}