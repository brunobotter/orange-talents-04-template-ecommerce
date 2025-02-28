package br.com.bruno.orange.mercadolivre.categoria;

public class CategoriaDto {

    private Long idCategoria;

    private String nomeCategoria;

    private String nomeCategoriaMae;

    public String getNomeCategoriaMae() {
        return nomeCategoriaMae;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    @Deprecated
    public CategoriaDto(){

    }

    public CategoriaDto(Categoria categoria) {
        this.idCategoria = categoria.getId();
        this.nomeCategoria = categoria.getNome();
        if(categoria.getCategoriaMae() != null){
            this.nomeCategoriaMae = categoria.getCategoriaMae().getNome();
        }
    }
}
