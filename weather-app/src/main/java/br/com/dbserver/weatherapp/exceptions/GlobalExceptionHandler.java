package br.com.dbserver.weatherapp.exceptions;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;
import java.util.Stack;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<String> handleUnexpectedTypeException(UnexpectedTypeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Faltou um atributo no JSON: " + e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado a entidade: " + e.getMessage());
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<String> handleDateTimeParseException(DateTimeParseException e) {
        String mensagem = "O formato da data está errada. O correto é dd/MM/yyyy. " + e.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST ).body(mensagem);
    }
}