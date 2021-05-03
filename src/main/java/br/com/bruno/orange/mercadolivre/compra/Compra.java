package br.com.bruno.orange.mercadolivre.compra;

import antlr.collections.List;
import br.com.bruno.orange.mercadolivre.produto.Produto;
import br.com.bruno.orange.mercadolivre.usuario.Usuario;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

    public Compra() {
    }

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

    public void adicionaTransacao(@Valid RetornoGatewayPagamento request) {
        Transacao novaTransacao = request.toTransacao(this);

        //1
        Assert.isTrue(!this.transacoes.contains(novaTransacao),
                "Já existe uma transacao igual a essa processada "
                        + novaTransacao);
        //1
        Assert.isTrue(transacoesConcluidasComSucesso().isEmpty(),"Esse compra já foi concluída com sucesso");

        this.transacoes.add(novaTransacao);
    }

    private Set<Transacao> transacoesConcluidasComSucesso() {
        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
                .filter(Transacao::concluidaComSucesso)
                .collect(Collectors.toSet());

        Assert.isTrue(transacoesConcluidasComSucesso.size() <= 1,"Deu ruim deu ruim deu ruim, tem mais de uma transacao concluida com sucesso aqui na compra "+this.id);

        return transacoesConcluidasComSucesso;
    }

    public boolean processadaComSucesso() {
        return !transacoesConcluidasComSucesso().isEmpty();
    }

    public Set<Transacao> getTransacoes() {
        return transacoes;
    }
}
