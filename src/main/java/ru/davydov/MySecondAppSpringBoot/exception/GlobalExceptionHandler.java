package ru.davydov.MySecondAppSpringBoot.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.davydov.MySecondAppSpringBoot.model.Codes;
import ru.davydov.MySecondAppSpringBoot.model.ErrorCodes;
import ru.davydov.MySecondAppSpringBoot.model.ErrorMessages;
import ru.davydov.MySecondAppSpringBoot.model.Response;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ValidationFailedException.class)
    public ResponseEntity<Response> handleValidation(ValidationFailedException e) {
        log.error("Ошибка валидации: {}", e.getMessage());

        Response response = Response.builder()
                .code(Codes.FAILED)
                .errorCode(ErrorCodes.VALIDATION_EXCEPTION)
                .errorMessage(ErrorMessages.VALIDATION)
                .build();

        log.error("response с ошибкой: {}", response);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnsupportedCodeException.class)
    public ResponseEntity<Response> handleUnsupportedCode(UnsupportedCodeException e) {
        log.error("Ошибка с uid: {}", e.getMessage());

        Response response = Response.builder()
                .code(Codes.FAILED)
                .errorCode(ErrorCodes.UNSUPPORTED_CODE_EXCEPTION)
                .errorMessage(ErrorMessages.UNSUPPORTED_CODE)
                .build();

        log.error("response с ошибкой: {}", response);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleUnknown(Exception e) {
        log.error("Неизвестная ошибка: {}", e.getMessage(), e);

        Response response = Response.builder()
                .code(Codes.FAILED)
                .errorCode(ErrorCodes.UNKNOWN_EXCEPTION)
                .errorMessage(ErrorMessages.UNKNOWN)
                .build();

        log.error("response с ошибкой: {}", response);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
