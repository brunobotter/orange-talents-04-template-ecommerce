package br.com.bruno.orange.mercadolivre.compra;

import javax.validation.constraints.NotNull;

public class RankingNovaCompraRequest {

    @NotNull
    private Long idCompra;
    @NotNull
    private Long idDonoProduto;

    public RankingNovaCompraRequest(Long idCompra, Long idDonoProduto) {
        super();
        this.idCompra = idCompra;
        this.idDonoProduto = idDonoProduto;
    }

    @Override
    public String toString() {
        return "RankingNovaCompraRequest{" +
                "idCompra=" + idCompra +
                ", idDonoProduto=" + idDonoProduto +
                '}';
    }
}
