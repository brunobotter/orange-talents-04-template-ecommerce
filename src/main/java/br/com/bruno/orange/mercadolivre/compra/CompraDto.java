package br.com.bruno.orange.mercadolivre.compra;

import br.com.bruno.orange.mercadolivre.produto.Produto;
import br.com.bruno.orange.mercadolivre.usuario.Usuario;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CompraDto {

    private String urlRetorno;

    private long id;

    private String nomeProduto;

    private int quantidade;

    private String nomeComprador;

    private BigDecimal valorProduto;

    private StatusCompra status;

    private Gateway gateway;
    @Deprecated
    public CompraDto() {
    }

    public CompraDto(Compra compra, String url) {
        this.id = compra.getId();
        this.gateway = compra.getGateway();
        this.nomeComprador = compra.getUsuario().getEmail();
        this.nomeProduto = compra.getProduto().getNome();
        this.quantidade = compra.getQuantidade();
        this.valorProduto = compra.getProduto().getValor();;
        this.status = compra.getStatus();
        this.urlRetorno = url;
    }

    public String getUrlRetorno() {
        return urlRetorno;
    }

    public long getId() {
        return id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getNomeComprador() {
        return nomeComprador;
    }

    public BigDecimal getValorProduto() {
        return valorProduto;
    }

    public StatusCompra getStatus() {
        return status;
    }

    public Gateway getGateway() {
        return gateway;
    }
}
