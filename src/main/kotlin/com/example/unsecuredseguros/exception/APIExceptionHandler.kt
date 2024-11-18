package com.example.unsecuredseguros.exception

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus


@ControllerAdvice
class APIExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class, NumberFormatException::class, ValidationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handlerBadRequest(request: HttpServletRequest, e: Exception): ErrorRespuesta {
        return ErrorRespuesta(message = e.message!!, uri = request.requestURI)
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    fun handlerNotfound(request: HttpServletRequest, e: Exception): ErrorRespuesta {
        return ErrorRespuesta(message = e.message!!, uri = request.requestURI)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun handlerGeneric(request: HttpServletRequest, e: Exception): ErrorRespuesta {
        return ErrorRespuesta(message = "Error Ineperado", uri = request.requestURI)
    }

}