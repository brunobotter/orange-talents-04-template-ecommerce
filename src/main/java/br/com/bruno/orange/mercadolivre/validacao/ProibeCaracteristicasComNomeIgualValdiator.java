package br.com.bruno.orange.mercadolivre.validacao;

import br.com.bruno.orange.mercadolivre.produto.ProdutoForm;
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
