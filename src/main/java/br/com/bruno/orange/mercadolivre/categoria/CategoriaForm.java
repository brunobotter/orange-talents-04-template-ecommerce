package br.com.bruno.orange.mercadolivre.categoria;

import br.com.bruno.orange.mercadolivre.validacao.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CategoriaForm {

    @NotBlank
    @UniqueValue(message = "Nome da categoria ja cadastrado no sistema", fieldName = "nome",domainClass = Categoria.class)
    private String nome;

    @Positive
    private Long idCategoriaMae;

    public Categoria toModel(EntityManager manager) {
        Categoria categoria = new Categoria(nome);
        if(idCategoriaMae != null){
            Categoria categoriaMae = manager.find(Categoria.class, idCategoriaMae);
            categoria.setCategoriaMae(categoriaMae);
        }
        return  categoria;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdCategoriaMae() {
        return idCategoriaMae;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdCategoriaMae(Long idCategoriaMae) {
        this.idCategoriaMae = idCategoriaMae;
    }
}
