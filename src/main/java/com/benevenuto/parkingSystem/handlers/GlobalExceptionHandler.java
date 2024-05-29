package com.benevenuto.parkingSystem.handlers;

import com.benevenuto.parkingSystem.domain.ExceptionMessage;
import com.benevenuto.parkingSystem.exceptions.VeiculoNotFoundException;
import com.benevenuto.parkingSystem.exceptions.VeiculoNotValid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VeiculoNotFoundException.class)
    public ResponseEntity<ExceptionMessage> HandleVeiculoNotFound(Exception exception)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ExceptionMessage(exception.getMessage()));
    }

    @ExceptionHandler(VeiculoNotValid.class)
    public ResponseEntity<ExceptionMessage> HandleVeiculoNotValid(Exception exception)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ExceptionMessage(exception.getMessage()));
    }

    public ResponseEntity<ExceptionMessage> GenericException(Exception exception)
    {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionMessage("Ja estamos olhando :)"));
    }
}
