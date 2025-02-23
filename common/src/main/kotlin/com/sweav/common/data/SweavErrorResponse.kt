package com.sweav.common.data

import com.fasterxml.jackson.annotation.JsonIgnore

interface SweavErrorResponse {
    val code: String
    val message: String

    companion object {
        fun of(errorType: SweavErrorType, message: String = errorType.message)
        = SweavSimpleErrorResponse(errorType, message)
    }
}

data class SweavSimpleErrorResponse(
    @JsonIgnore
    val errorType: SweavErrorType,
    override val message: String = errorType.message
) : SweavErrorResponse {
    override val code = errorType.code
}
