package br.com.bruno.orange.mercadolivre.compra;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

public class PagSeguroForm implements RetornoGatewayPagamento {

    @NotNull
    private String idTransacao;

    @NotNull
    private StatusRetornoPagSeguro statusPagSeguro;

    @Deprecated
    public PagSeguroForm() {
    }

    public PagSeguroForm(String idTransacao, StatusRetornoPagSeguro statusPagSeguro) {
        this.idTransacao = idTransacao;
        this.statusPagSeguro = statusPagSeguro;
    }

    public String getIdTransacao() {
        return idTransacao;
    }

    public StatusRetornoPagSeguro getStatusPagSeguro() {
        return statusPagSeguro;
    }

    public Transacao toTransacao(Compra compra) {
        return  new Transacao(statusPagSeguro.normaliza(), idTransacao, compra);
    }



}
