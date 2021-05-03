package br.com.bruno.orange.mercadolivre.compra;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping
public class OutroSistemaController {

    @PostMapping(value = "/notas-fiscais")
    public void criaNota(@Valid @RequestBody NovaCompraNFRequest request) throws InterruptedException {
        System.out.println("criando nota "+request);
        Thread.sleep(150);
    }

    @PostMapping(value = "/ranking")
    public void ranking(@Valid @RequestBody RankingNovaCompraRequest request) throws InterruptedException {
        System.out.println("criando ranking"+request);
        Thread.sleep(150);
    }
}
