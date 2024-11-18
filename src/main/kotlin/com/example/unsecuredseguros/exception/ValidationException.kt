package com.example.unsecuredseguros.exception

class ValidationException(message: String)
    : Exception("Error en la validación (400). $message") {

}