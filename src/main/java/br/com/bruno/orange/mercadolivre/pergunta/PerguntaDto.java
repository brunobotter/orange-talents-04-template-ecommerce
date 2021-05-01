package br.com.bruno.orange.mercadolivre.pergunta;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class PerguntaDto {

    private String titulo;

    @JsonFormat(pattern = "dd/MM/yyyy HH:ss:mm",shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataCriacao;

    public PerguntaDto(Pergunta pergunta) {
        this.titulo = pergunta.getTitulo();
        this.dataCriacao = pergunta.getDataCriacao();
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
