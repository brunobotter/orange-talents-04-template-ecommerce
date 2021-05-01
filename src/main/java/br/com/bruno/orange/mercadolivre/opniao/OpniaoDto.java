package br.com.bruno.orange.mercadolivre.opniao;

public class OpniaoDto {

    private String nomeProduto;

    private int nota;

    private String titulo;

    private String descricao;


    public OpniaoDto(Opniao opniao) {
        this.nota = opniao.getNota();
        this.descricao = opniao.getDescricao();
        this.titulo = opniao.getTitulo();
        this.nomeProduto = opniao.getProduto().getNome();
    }

    public String getNomeProduto() {
        return nomeProduto;
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
