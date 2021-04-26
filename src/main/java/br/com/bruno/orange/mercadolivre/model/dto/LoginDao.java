package br.com.bruno.orange.mercadolivre.model.dto;

import br.com.bruno.orange.mercadolivre.model.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class LoginDao {

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataCriacao;

    public LoginDao(Usuario usuario) {
        this.dataCriacao = usuario.getDataCriacao();
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
