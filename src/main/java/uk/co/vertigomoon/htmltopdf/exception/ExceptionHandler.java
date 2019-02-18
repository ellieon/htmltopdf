package uk.co.vertigomoon.htmltopdf.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
            (value = { IllegalArgumentException.class })
    protected ResponseEntity<Object> handleBadRequest(
            RuntimeException ex,
            WebRequest request) {
        return handleExceptionInternal(ex, "",
                new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
            (value = { IllegalStateException.class })
    protected ResponseEntity<Object> handleInternalError(
            RuntimeException ex,
            WebRequest request) {
        return handleExceptionInternal(ex, "",
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
