package br.com.bruno.orange.mercadolivre.usuario;

import br.com.bruno.orange.mercadolivre.usuario.ConverterSenha;
import br.com.bruno.orange.mercadolivre.usuario.Usuario;
import br.com.bruno.orange.mercadolivre.validacao.UniqueValue;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

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
