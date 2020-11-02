package com.gigrt.currencyexchanger.exceptions;

import com.gigrt.currencyexchanger.api.ApiError;
import com.gigrt.currencyexchanger.api.ApiSubError;
import com.gigrt.currencyexchanger.api.ApiValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = ErrorMessages.NOT_READABLE.getMessage(request.getLocale());
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            EntityNotFoundException ex) {
        LOG.error("Exception {}", ex.getMessage(), ex);
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleEntityConstraintViolation(
            ConstraintViolationException ex, Locale locale) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        String message = ErrorMessages.INVALID_FIELDS.getMessage(locale);
        apiError.setMessage(message);
        List<ApiSubError> apiSubErrors = new ArrayList<>();
        for (ConstraintViolation violation : ex.getConstraintViolations()) {
            apiSubErrors.add(
                    new ApiValidationError(
                            violation.getRootBeanClass().getSimpleName(),
                            violation.getPropertyPath().toString(),
                            violation.getInvalidValue(),
                            violation.getMessage())
            );
        }
        apiError.setSubErrors(apiSubErrors);
        return buildResponseEntity(apiError);
    }
    //other exception handlers below

}