package br.com.bruno.orange.mercadolivre.excecao;

import java.util.ArrayList;
import java.util.List;

public class ValidacaoErrorSaida {

    private List<String> globalErrorMessage = new ArrayList<>();
    private List<FieldErrorSaida> fieldErrorMessage = new ArrayList<>();

    public void addError(String message) {
        globalErrorMessage.add(message);
    }

    public void addFieldError(String field, String message) {
        FieldErrorSaida fieldError = new FieldErrorSaida(field, message);
        fieldErrorMessage.add(fieldError);
    }

    public List<String> getGlobalErrorMessage() {
        return globalErrorMessage;
    }

    public List<FieldErrorSaida> getFieldErrorMessage() {
        return fieldErrorMessage;
    }
}
