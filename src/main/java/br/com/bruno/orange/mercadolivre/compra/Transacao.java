package br.com.bruno.orange.mercadolivre.compra;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime instante;
    @NotNull
    private StatusTransacao status;
    @NotBlank
    private String idTransacao;

    @ManyToOne
    @NotNull
    @Valid
    private Compra compra;

    @Deprecated
    public Transacao() {
    }

    public Transacao(StatusTransacao status, @NotNull String idTransacao, Compra compra) {
        this.status = status;
        this.idTransacao = idTransacao;
        this.instante = LocalDateTime.now();
        this.compra = compra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transacao)) return false;
        Transacao transacao = (Transacao) o;
        return Objects.equals(instante, transacao.instante) && status == transacao.status && Objects.equals(idTransacao, transacao.idTransacao) && Objects.equals(compra, transacao.compra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instante, status, idTransacao, compra);
    }

    public boolean concluidaComSucesso() {
        return this.status.equals(StatusTransacao.SUCESSO);
    }


    public Long getId() {
        return id;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public StatusTransacao getStatus() {
        return status;
    }

    public String getIdTransacao() {
        return idTransacao;
    }

    public Compra getCompra() {
        return compra;
    }


}
