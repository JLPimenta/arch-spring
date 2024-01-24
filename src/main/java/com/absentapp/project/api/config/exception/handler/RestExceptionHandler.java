package com.absentapp.project.api.config.exception.handler;

import com.absentapp.project.domain.core.exception.DomainException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String ERRO_NAO_ESPERADO = "Erro não esperado.";

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseError> handleConstraintException(Exception ex, WebRequest request) {
        log.info(ERRO_NAO_ESPERADO, ex);

        HttpMethod method = ((ServletWebRequest) request).getHttpMethod();

        if (ex.getCause() instanceof ConstraintViolationException
                && method == HttpMethod.DELETE) {
            return new ResponseEntity<>(ResponseError.constraintException(ex), HttpStatus.BAD_REQUEST);
        }
        if (ex.getCause() instanceof DataException) {
            return new ResponseEntity<>(ResponseError.entityDataException(ex), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ResponseError.notMappedException(ex), HttpStatus.BAD_REQUEST);
    }

    @Override
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.info(ERRO_NAO_ESPERADO, ex);
        return new ResponseEntity<>(ResponseError.badRequest(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @Override
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.info(ERRO_NAO_ESPERADO, ex);
        return new ResponseEntity<>(ResponseError.badRequest(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseError> handleNotFoundException(Exception ex) {
        return new ResponseEntity<>(ResponseError.notFound(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DomainException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ResponseError> handleDomainException(Exception ex) {
        log.info(ERRO_NAO_ESPERADO, ex);
        return new ResponseEntity<>(ResponseError.badRequest(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseError> handleGlobalException(Exception ex) {
        log.info(ERRO_NAO_ESPERADO, ex);
        return new ResponseEntity<>(ResponseError.notMappedException(ex), HttpStatus.BAD_REQUEST);
    }
}
