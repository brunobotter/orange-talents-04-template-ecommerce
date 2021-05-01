package br.com.bruno.orange.mercadolivre.compra;

import br.com.bruno.orange.mercadolivre.excecao.ValidacaoErrorSaida;
import br.com.bruno.orange.mercadolivre.produto.Produto;
import br.com.bruno.orange.mercadolivre.usuario.Usuario;
import br.com.bruno.orange.mercadolivre.validacao.VerificaEstoque;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraForm {

    //PAYPAL / PAGSEGURO
    @Enumerated(EnumType.STRING)
    private Gateway gateway;

    @Positive
    private Long idProduto;

    @NotNull
    @Positive
    @VerificaEstoque(fieldName = "quantidade", domainClass = Produto.class)
    private int quantidade;

    public CompraForm(Gateway gateway, Long idProduto, int quantidade) {
        this.gateway = gateway;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }


    public Compra toModel(Usuario usuarioLogado, EntityManager manager) throws Exception {
        Produto produto = manager.find(Produto.class, idProduto);
        boolean abateu = produto.diminuiEstoque(quantidade);
        if(abateu){
            manager.merge(produto);
            return  new Compra(gateway, produto, usuarioLogado, quantidade);
        }else{
            BindException bind = new BindException(produto, "CompraForm");
            bind.reject(null, "Nao foi possivel realizar a compra pelo estoque estar indisponivel");
            throw bind;
        }

    }

}
