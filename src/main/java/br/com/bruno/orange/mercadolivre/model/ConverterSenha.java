package br.com.bruno.orange.mercadolivre.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;

public class ConverterSenha {

    private String senha;

    public ConverterSenha(@NotBlank @Length(min = 6) String senha){
        super();
        this.senha = senha;
    }

    public String hash(){
        return new BCryptPasswordEncoder().encode(senha);
    }
}
