package br.com.bruno.orange.mercadolivre.usuario;

import br.com.bruno.orange.mercadolivre.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class UsuarioDto {

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataCriacao;

    public UsuarioDto(Usuario usuario) {
        this.dataCriacao = usuario.getDataCriacao();
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
