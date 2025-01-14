package br.com.bruno.orange.mercadolivre.imagem;

import br.com.bruno.orange.mercadolivre.produto.Produto;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @NotNull
    @ManyToOne
    private Produto produto;

    @URL
    @NotBlank
    private String link;

    public ImagemProduto() {
    }

    public ImagemProduto(@NotNull @Valid Produto produto, @URL String link) {
        this.produto = produto;
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
