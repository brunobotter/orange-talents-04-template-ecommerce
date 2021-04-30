package br.com.bruno.orange.mercadolivre.model;

import br.com.bruno.orange.mercadolivre.model.form.CaracteristicasForm;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private @NotBlank String nome;
    private @Positive int quantidade;
    private @NotBlank @Length(max = 1000) String descricao;
    private @NotNull @Positive BigDecimal valor;

    @NotNull
    @Valid
    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    @NotNull
    @Valid
    private Categoria categoria;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private List<Opniao> opnioes = new ArrayList<>();

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, int quantidade, BigDecimal valor, String descricao,
                   Categoria categoria, Usuario usuario,
                   @Valid Collection<CaracteristicasForm> caracteristicas) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.valor = valor;
        this.usuario = usuario;
        this.caracteristicas.addAll(caracteristicas.stream().map(
                caracteristica -> caracteristica.toModel(this)).collect(Collectors.toSet()));
        Assert.isTrue(this.caracteristicas.size() >= 3, "Todo produto tem de ter mais de 3 caracterisricas");

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Set<CaracteristicaProduto> getCaracteristicas() {
        return caracteristicas;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return getQuantidade() == produto.getQuantidade() && Objects.equals(getId(), produto.getId()) && Objects.equals(getNome(), produto.getNome()) && Objects.equals(getDescricao(), produto.getDescricao()) && Objects.equals(getValor(), produto.getValor()) && Objects.equals(getUsuario(), produto.getUsuario()) && Objects.equals(getCategoria(), produto.getCategoria()) && Objects.equals(getCaracteristicas(), produto.getCaracteristicas()) && Objects.equals(imagens, produto.imagens) && Objects.equals(opnioes, produto.opnioes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getQuantidade(), getDescricao(), getValor(), getUsuario(), getCategoria(), getCaracteristicas(), imagens, opnioes);
    }

    public void associaImagens(Set<String> links) {
        Set<ImagemProduto> imagensAssociadas = links.stream().map(link -> new ImagemProduto(this,link)).collect(Collectors.toSet());
        this.imagens.addAll(imagensAssociadas);
    }

    public boolean pertenceAoUsuario(Usuario usuarioPertenceProduto) {
        return this.usuario.getEmail().equals(usuarioPertenceProduto.getEmail());
    }


}
