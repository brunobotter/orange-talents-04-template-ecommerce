package br.com.bruno.orange.mercadolivre.email;

import org.springframework.stereotype.Component;

@Component
public interface EnvioEmail {

    void enviar(String corpo, String assunto, String nomeDeQuemEnviou, String aPartirDe, String para);
}
