package br.com.bruno.orange.mercadolivre.email;

import br.com.bruno.orange.mercadolivre.compra.Compra;
import br.com.bruno.orange.mercadolivre.pergunta.Pergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Component
public class Email {

    @Autowired
    private EnvioEmail email;

    public void novaPergunta(@NotNull @Valid Pergunta pergunta){
        email.enviar("<html></html>","Nova pergunta..",pergunta.getUsuario().getUsername(),pergunta.getUsuario().getEmail(),"perguntas@email.com");
    }

    public void novaCompra(@NotNull @Valid Compra compra){
        email.enviar("<html></html>","Nova Compra..",compra.getUsuario().getUsername(),compra.getUsuario().getEmail(),compra.getProduto().getUsuario().getEmail());

    }
}
