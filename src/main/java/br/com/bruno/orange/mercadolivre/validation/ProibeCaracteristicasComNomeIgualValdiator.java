package br.com.bruno.orange.mercadolivre.validation;

import br.com.bruno.orange.mercadolivre.model.form.ProdutoForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

public class ProibeCaracteristicasComNomeIgualValdiator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ProdutoForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }
        ProdutoForm form = (ProdutoForm) target;
        Set<String> nomesRepetidos = form.buscaCaracteristicasIguais();
        if(!nomesRepetidos.isEmpty()){
            errors.rejectValue("caracteristicas",null, "Caracteristicas repetidas"+nomesRepetidos);
        }
    }
}
