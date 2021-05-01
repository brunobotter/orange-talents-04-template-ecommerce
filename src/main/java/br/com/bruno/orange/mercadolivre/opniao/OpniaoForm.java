package br.com.bruno.orange.mercadolivre.opniao;

import br.com.bruno.orange.mercadolivre.produto.Produto;
import br.com.bruno.orange.mercadolivre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

public class OpniaoForm {

   @Min(1)
   @Max(5)
    private int nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Length(max = 500)
    private String descricao;


    @Deprecated
    public OpniaoForm(){

}

    public OpniaoForm(int nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Opniao toModel(Produto produto, Usuario usuario) {
        return new Opniao(nota, titulo, descricao, produto, usuario);
    }

    public int getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }


}
