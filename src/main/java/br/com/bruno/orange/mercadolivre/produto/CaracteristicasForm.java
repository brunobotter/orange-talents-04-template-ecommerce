package br.com.bruno.orange.mercadolivre.produto;

import br.com.bruno.orange.mercadolivre.produto.CaracteristicaProduto;
import br.com.bruno.orange.mercadolivre.produto.Produto;

import javax.validation.constraints.NotBlank;

public class CaracteristicasForm {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public CaracteristicasForm(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public CaracteristicaProduto toModel(Produto produto) {
        return new CaracteristicaProduto(nome, descricao, produto);
    }
}
