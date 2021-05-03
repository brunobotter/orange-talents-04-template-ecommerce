package br.com.bruno.orange.mercadolivre.compra;

public enum StatusRetornoPagSeguro {

    SUCESSO, ERRO;

    public StatusTransacao normaliza() {
        if(this.equals(SUCESSO)){
            return StatusTransacao.SUCESSO;
        }
        return StatusTransacao.ERRO;
    }
}
