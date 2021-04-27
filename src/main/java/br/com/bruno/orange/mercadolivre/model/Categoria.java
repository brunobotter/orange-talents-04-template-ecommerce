package br.com.bruno.orange.mercadolivre.model;

import br.com.bruno.orange.mercadolivre.model.form.CategoriaForm;
import br.com.bruno.orange.mercadolivre.validation.UniqueValue;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Categoria categoriaMae;

    @NotBlank
    private String nome;

    @Deprecated
    public Categoria() {
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public void setCategoriaMae(Categoria categoriaMae) {
        this.categoriaMae = categoriaMae;
    }

    public Long getId() {
        return id;
    }

    public Categoria getCategoriaMae() {
        return categoriaMae;
    }

    public String getNome() {
        return nome;
    }
}
