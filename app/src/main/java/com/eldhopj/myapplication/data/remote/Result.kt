package com.eldhopj.myapplication.data.remote

import com.eldhopj.myapplication.utils.constants.StringConstants

/**
 * Network response
 *
 * @param T
 * @constructor Create empty Network response
 * @author eldhopj
 */
sealed class Result<out T> {

    data class Loading(val isLoading: Boolean) : Result<Nothing>()

    data class Success<out T>(val data: T?) : Result<T>()

    data class Error(val errorData: ErrorData) : Result<Nothing>()

    data class Exception(val throwable: Throwable) : Result<Nothing>()
}

class ErrorData(val code: Int, val errorMessage: String? = StringConstants.UNKNOWN_ERROR)
