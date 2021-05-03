package br.com.bruno.orange.mercadolivre.compra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class RetornoCompraController {


    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private EventosNovaCompra eventosNovaCompra;

    @PostMapping(value = "/retorno-pagseguro/{id}")
    @Transactional
    public String processamentoPagSeguro(@PathVariable("id") Long idCompra, @RequestBody  PagSeguroForm form) {
        return processa(idCompra, form);
    }

    @PostMapping(value = "/retorno-paypal/{id}")
    @Transactional
    public String processamentoPaypal(@PathVariable("id") Long idCompra, @RequestBody PaypalForm form) {
        return processa(idCompra, form);
    }


    private String processa(Long idCompra,RetornoGatewayPagamento retornoGatewayPagamento) {

        Compra compra = manager.find(Compra.class, idCompra);
        compra.adicionaTransacao(retornoGatewayPagamento);
        manager.merge(compra);
        eventosNovaCompra.processa(compra);

        return compra.toString();
    }

}
