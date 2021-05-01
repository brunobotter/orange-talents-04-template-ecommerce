package br.com.bruno.orange.mercadolivre.pergunta;

import br.com.bruno.orange.mercadolivre.produto.Produto;
import br.com.bruno.orange.mercadolivre.usuario.Usuario;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class PerguntaForm {

    @NotBlank
    private String titulo;

    private LocalDateTime dataCriacao;

    public PerguntaForm(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }



    public Pergunta toModel(Usuario usuario, Produto produto) {
        return new Pergunta(titulo, usuario, produto,dataCriacao);
    }

    @Deprecated
    public PerguntaForm() {
    }


}
