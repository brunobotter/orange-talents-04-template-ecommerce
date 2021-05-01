package br.com.bruno.orange.mercadolivre.produto;

import br.com.bruno.orange.mercadolivre.categoria.Categoria;
import br.com.bruno.orange.mercadolivre.usuario.Usuario;
import br.com.bruno.orange.mercadolivre.validacao.ExisteId;
import br.com.bruno.orange.mercadolivre.validacao.UniqueValue;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProdutoForm {

    @NotBlank
    @UniqueValue(domainClass = Produto.class, fieldName = "nome")
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @Positive
    private int quantidade;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @NotNull
    @ExisteId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoria;


    @Size(min = 3)
    private List<CaracteristicasForm> caracteristicas = new ArrayList<>();

    @Deprecated
    public ProdutoForm() {
    }

    public ProdutoForm(String nome, BigDecimal valor, int quantidade, String descricao, Long idCategoria, List<CaracteristicasForm> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
        this.caracteristicas.addAll(caracteristicas);
    }


    public Produto toModel(EntityManager manager, Usuario usuario) {
        Categoria categoria = manager.find(Categoria.class, idCategoria);

        return new Produto(nome, quantidade, valor, descricao, categoria, usuario, caracteristicas);
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

    public Long getIdCategoria() {
        return idCategoria;
    }

    public Set<String> buscaCaracteristicasIguais() {
        HashSet<String> nomesIguais = new HashSet();
        HashSet<String> resultados = new HashSet();
        for(CaracteristicasForm caracteristica: caracteristicas){
            String nome = caracteristica.getNome();
            if(!nomesIguais.add(nome)){
                resultados.add(nome);
            }
        }
        return resultados;
    }

    public List<CaracteristicasForm> getCaracteristicas() {
        return caracteristicas;
    }
}
