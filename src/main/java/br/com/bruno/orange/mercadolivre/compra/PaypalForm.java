package br.com.bruno.orange.mercadolivre.compra;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PaypalForm implements RetornoGatewayPagamento {

    @Min(0)
    @Max(1)
    private int status;
    @NotBlank
    private String idTransacao;

    public PaypalForm(@Min(0) @Max(1) int status,
                                @NotBlank String idTransacao) {
        super();
        this.status = status;
        this.idTransacao = idTransacao;
    }

    public Transacao toTransacao(Compra compra) {
        StatusTransacao statusCalculado = this.status == 0 ? StatusTransacao.ERRO
                : StatusTransacao.SUCESSO;

        return new Transacao(statusCalculado, idTransacao, compra);
    }

    public int getStatus() {
        return status;
    }

    public String getIdTransacao() {
        return idTransacao;
    }
}
