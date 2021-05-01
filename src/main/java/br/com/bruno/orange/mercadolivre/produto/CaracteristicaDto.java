package br.com.bruno.orange.mercadolivre.produto;

import br.com.bruno.orange.mercadolivre.produto.CaracteristicaProduto;

public class CaracteristicaDto {

    private String nome;

    private String descricao;

    @Deprecated
    public CaracteristicaDto() {
    }

    public CaracteristicaDto(CaracteristicaProduto caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }


    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
