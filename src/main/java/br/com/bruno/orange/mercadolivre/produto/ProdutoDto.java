package br.com.bruno.orange.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoDto {

    private Long id;
    private String nome;
    private BigDecimal valor;
    private int quantidade;
    private String descricao;
    private String nomeCategoria;
    private List<CaracteristicaDto> caracteristicas = new ArrayList<>();

    @Deprecated
    public ProdutoDto() {
    }

    public ProdutoDto(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.quantidade = produto.getQuantidade();
        this.descricao = produto.getDescricao();;
        this.nomeCategoria = produto.getCategoria().getNome();
        this.caracteristicas.addAll(produto.getCaracteristicas().stream().map(
                caracteristica -> new CaracteristicaDto(caracteristica)).collect(Collectors.toList())) ;
    }


    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public Long getId() {
        return id;
    }

    public List<CaracteristicaDto> getCaracteristicas() {
        return caracteristicas;
    }
}
