package br.com.bruno.orange.mercadolivre.imagem;

public class ImagemProdutoDto {

    private String link;

    public ImagemProdutoDto(ImagemProduto imagem) {
        this.link = imagem.getLink();
    }

    public String getLink() {
        return link;
    }
}
