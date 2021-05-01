package br.com.bruno.orange.mercadolivre.produto;

import br.com.bruno.orange.mercadolivre.imagem.ImagemProduto;
import br.com.bruno.orange.mercadolivre.imagem.ImagemProdutoDto;
import br.com.bruno.orange.mercadolivre.opniao.OpniaoDto;
import br.com.bruno.orange.mercadolivre.pergunta.PerguntaDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DetalhesProdutoDto {

    private Long idProduto;
    private String nomeProduto;
    private BigDecimal precoProduto;
    private List<CaracteristicaDto> caracteristicasProduto = new ArrayList<>();
    private List<ImagemProdutoDto> linksImagensProduto = new ArrayList<>();
    private String descricaoProduto;
    private List<OpniaoDto> opniaoProdutos = new ArrayList<>();
    private List<PerguntaDto> perguntaProdutos = new ArrayList<>();
    private int totalNotasProduto;
    private BigDecimal mediaNotaProduto;


    public DetalhesProdutoDto(Produto produto) {
        this.idProduto = produto.getId();;
        this.nomeProduto = produto.getNome();
        this.precoProduto = produto.getValor();
        this.descricaoProduto = produto.getDescricao();
        this.caracteristicasProduto.addAll(produto.getCaracteristicas().stream().map(prod
        -> new CaracteristicaDto(prod)).collect(Collectors.toList()));
        this.linksImagensProduto.addAll(produto.getImagens().stream().map(imagem
                -> new ImagemProdutoDto(imagem)).collect(Collectors.toList()));
        this.opniaoProdutos.addAll(produto.getOpnioes().stream().map(op
        -> new OpniaoDto(op)).collect(Collectors.toList()));
        this.perguntaProdutos.addAll(produto.getPerguntas().stream().map(pergunta
                -> new PerguntaDto(pergunta)).collect(Collectors.toList()));
        this.totalNotasProduto = produto.totalNotas(produto);
        this.mediaNotaProduto = produto.mediaProduto(produto);
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public BigDecimal getPrecoProduto() {
        return precoProduto;
    }

    public List<CaracteristicaDto> getCaracteristicasProduto() {
        return caracteristicasProduto;
    }

    public List<ImagemProdutoDto> getLinksImagensProduto() {
        return linksImagensProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public List<OpniaoDto> getOpniaoProdutos() {
        return opniaoProdutos;
    }

    public List<PerguntaDto> getPerguntaProdutos() {
        return perguntaProdutos;
    }

    public int getTotalNotasProduto() {
        return totalNotasProduto;
    }

    public BigDecimal getMediaNotaProduto() {
        return mediaNotaProduto;
    }
}
