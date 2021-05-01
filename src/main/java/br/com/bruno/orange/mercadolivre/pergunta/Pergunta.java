package br.com.bruno.orange.mercadolivre.pergunta;

import br.com.bruno.orange.mercadolivre.produto.Produto;
import br.com.bruno.orange.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @ManyToOne
    @NotNull
    @Valid
    private Usuario usuario;

    @ManyToOne
    @NotNull
    @Valid
    private Produto produto;

    private LocalDateTime dataCriacao;

    public Pergunta(String titulo, Usuario usuario, Produto produto, LocalDateTime dataCriacao) {
        this.titulo = titulo;
        this.usuario = usuario;
        this.produto = produto;
        this.dataCriacao = dataCriacao.now();
    }

    @Deprecated
    public Pergunta() {
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Produto getProduto() {
        return produto;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
