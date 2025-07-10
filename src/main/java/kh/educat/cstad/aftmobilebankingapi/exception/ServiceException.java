package kh.educat.cstad.aftmobilebankingapi.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ServiceException {

    @ExceptionHandler(ResponseStatusException.class)

    public ResponseEntity<?> handleResponseStatusException(
            ResponseStatusException ex
    ){

        ErrorResponse<?> error = ErrorResponse.builder()
                .message("Service business logic error")
                .code(ex.getStatusCode().value())
                .timestamp(LocalDateTime.now())
                .details(ex.getReason())
                .build();


        return ResponseEntity
                .status(ex.getStatusCode().value())
                .body(error);
    }


}
