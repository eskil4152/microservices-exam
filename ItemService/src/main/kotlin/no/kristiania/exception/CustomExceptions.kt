package no.kristiania.exception

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class CustomExceptions {

    @ExceptionHandler
    fun handleItemNotFoundException(exception: ItemNotFoundException): ResponseEntity<String> {
        val exceptionPayload = ExceptionPayload(HttpStatus.BAD_REQUEST.value(), exception.message?: "Item not found")
        return ResponseEntity(jacksonObjectMapper().writeValueAsString(exceptionPayload), HttpStatus.BAD_REQUEST)
    }
}

data class ExceptionPayload(
    val statusCode: Int,
    val message: String
)