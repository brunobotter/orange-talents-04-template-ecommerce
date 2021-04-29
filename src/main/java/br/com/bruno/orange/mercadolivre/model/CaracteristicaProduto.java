package br.com.bruno.orange.mercadolivre.model;

import br.com.bruno.orange.mercadolivre.model.dto.CaracteristicaDto;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class CaracteristicaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @ManyToOne
    @NotNull @Valid
    private Produto produto;

    public CaracteristicaProduto(String nome, String descricao, Produto produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }


    @Deprecated
    public CaracteristicaProduto() {
    }

    public CaracteristicaProduto(CaracteristicaDto caracteristica) {
        this.descricao = caracteristica.getDescricao();
        this.nome = caracteristica.getNome();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Produto getProduto() {
        return produto;
    }
}
