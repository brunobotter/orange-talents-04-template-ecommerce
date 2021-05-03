package br.com.bruno.orange.mercadolivre.compra;

import org.springframework.web.util.UriComponentsBuilder;

public enum Gateway {

    pagseguro {
        @Override
        String criaUrlRetorno(Compra compra, UriComponentsBuilder uri){
            String urlPagSeguro = uri.path("/retorno-pagseguro/{id}")
                    .buildAndExpand(compra.getId()).toString();
            return "pagseguro.com/"+compra.getId()+"?redirectUrl="+urlPagSeguro;
        }
    },
    paypal {
        @Override
        String criaUrlRetorno(Compra compra, UriComponentsBuilder uri){
            String urlPaypal = uri.path("/retorno-paypal/{id}")
                    .buildAndExpand(compra.getId()).toString();
            return "paypal.com/"+compra.getId()+"?redirectUrl="+urlPaypal;
        }
    };

    abstract String criaUrlRetorno(Compra compra,
                                   UriComponentsBuilder uriComponentsBuilder);
}
