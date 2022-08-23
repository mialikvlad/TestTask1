package com.example.data.repository

fun checkErrorCode(code: Int): Exception {
    return when (code) {
        in 400..499 -> Exception(CLIENT_ERROR)
        in 500..599 -> Exception(SERVER_ERROR)
        else -> Exception(UNEXPECTED_ERROR)
    }
}

private const val CLIENT_ERROR = "Client's error"
private const val SERVER_ERROR = "Server's error"
private const val UNEXPECTED_ERROR = "Unexpected error"