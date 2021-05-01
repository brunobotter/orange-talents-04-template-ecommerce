package br.com.bruno.orange.mercadolivre.email;

import org.springframework.stereotype.Component;

@Component
public class FakeEmail implements  EnvioEmail{
    @Override
    public void enviar(String corpo, String assunto, String nomeDeQuemEnviou, String aPartirDe, String para) {
        System.out.println(corpo);
        System.out.println(assunto);
        System.out.println(nomeDeQuemEnviou);
        System.out.println(aPartirDe);
        System.out.println(para);

    }
}
