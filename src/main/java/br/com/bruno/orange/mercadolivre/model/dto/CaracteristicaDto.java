package br.com.bruno.orange.mercadolivre.model.dto;

import br.com.bruno.orange.mercadolivre.model.CaracteristicaProduto;

import java.util.List;

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
