package br.com.bruno.orange.mercadolivre.model.dto;

import br.com.bruno.orange.mercadolivre.model.CaracteristicaProduto;
import br.com.bruno.orange.mercadolivre.model.Categoria;
import br.com.bruno.orange.mercadolivre.model.Produto;
import br.com.bruno.orange.mercadolivre.model.form.CaracteristicasForm;
import br.com.bruno.orange.mercadolivre.validation.ExisteId;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoDto {


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

    public List<CaracteristicaDto> getCaracteristicas() {
        return caracteristicas;
    }
}
