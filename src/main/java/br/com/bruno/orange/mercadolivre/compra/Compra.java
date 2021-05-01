package br.com.bruno.orange.mercadolivre.compra;

import br.com.bruno.orange.mercadolivre.produto.Produto;
import br.com.bruno.orange.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Valid
    @ManyToOne
    private Produto produto;

    @NotNull
    @Valid
    @ManyToOne
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private StatusCompra status;

    @Enumerated(EnumType.STRING)
    private Gateway gateway;

    @Positive
    @NotNull
    private int quantidade;

    public Compra(Gateway gateway, Produto produto, Usuario usuarioLogado, int quantidade) {
        this.gateway = gateway;
        this.produto = produto;
        this.usuario = usuarioLogado;
        this.quantidade = quantidade;
        this.status = StatusCompra.INICIADA;
    }

    public long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }


    public StatusCompra getStatus() {
        return status;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
