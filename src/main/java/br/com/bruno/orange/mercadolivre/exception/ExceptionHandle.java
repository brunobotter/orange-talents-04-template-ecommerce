package br.com.bruno.orange.mercadolivre.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionHandle {

    @Autowired
    private MessageSource source;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidacaoErrorSaida handlerValidationError(MethodArgumentNotValidException exception) {
        List<ObjectError> globalError = exception.getBindingResult().getAllErrors();
        List<FieldError> fieldError = exception.getBindingResult().getFieldErrors();
        return buildValidationError(globalError, fieldError);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ValidacaoErrorSaida handlerValidationError(BindException exception) {
        List<ObjectError> globalError =  exception.getBindingResult().getAllErrors();
        List<FieldError> fieldError = exception.getBindingResult().getFieldErrors();
        return buildValidationError(globalError, fieldError);
    }


    private ValidacaoErrorSaida buildValidationError(List<ObjectError> globalError, List<FieldError> fieldError) {
        ValidacaoErrorSaida validation = new ValidacaoErrorSaida();
        globalError.forEach(error -> validation.addError(getErrorMessage(error)));
        fieldError.forEach(error -> {
            String errorMessage = getErrorMessage(error);
            validation.addFieldError(error.getField(), errorMessage);
        });
        return validation;
    }

    private String getErrorMessage(ObjectError error) {

        return source.getMessage(error, LocaleContextHolder.getLocale());
    }
}
