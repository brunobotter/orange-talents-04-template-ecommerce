package br.com.bruno.orange.mercadolivre.compra;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class RetornoCompraDto {

    private String idTransacao;

    private String statusTransacao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:ss")
    private LocalDateTime instante;

    public RetornoCompraDto(Compra compra) {

    }

    public String getIdTransacao() {
        return idTransacao;
    }

    public String getStatusTransacao() {
        return statusTransacao;
    }

    public LocalDateTime getInstante() {
        return instante;
    }
}
