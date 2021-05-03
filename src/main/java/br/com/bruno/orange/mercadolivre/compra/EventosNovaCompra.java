package br.com.bruno.orange.mercadolivre.compra;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class EventosNovaCompra {


    private Set<EventoCompraSucesso> eventosCompraSucesso;


    public EventosNovaCompra(Set<EventoCompraSucesso> eventosCompraSucesso) {
        this.eventosCompraSucesso = eventosCompraSucesso;
    }

    public void processa(Compra compra) {
        //1
        if(compra.processadaComSucesso()) {
            //1
            eventosCompraSucesso.forEach(evento -> evento.processa(compra));
        }
        else {
            //eventosFalha
        }
    }


}
