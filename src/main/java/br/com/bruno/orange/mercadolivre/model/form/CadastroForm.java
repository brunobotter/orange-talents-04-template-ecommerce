package br.com.bruno.orange.mercadolivre.model.form;

import br.com.bruno.orange.mercadolivre.model.ConverterSenha;
import br.com.bruno.orange.mercadolivre.model.Usuario;
import br.com.bruno.orange.mercadolivre.validation.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class CadastroForm {

    @NotBlank
    @NotNull
    @Email
    @UniqueValue(domainClass = Usuario.class, fieldName = "email", message = "O email ja existe no banco de dados!")
    private String email;

    @Length(min = 6)
    @NotBlank
    @NotNull
    private String senha;

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario toModel() {
        return new Usuario(email, new ConverterSenha(senha));
    }

   }
