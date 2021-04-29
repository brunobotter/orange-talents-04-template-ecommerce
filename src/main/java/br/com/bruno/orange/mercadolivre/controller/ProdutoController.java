package br.com.bruno.orange.mercadolivre.controller;

import br.com.bruno.orange.mercadolivre.model.Produto;
import br.com.bruno.orange.mercadolivre.model.Usuario;
import br.com.bruno.orange.mercadolivre.model.dto.ProdutoDto;
import br.com.bruno.orange.mercadolivre.model.form.ProdutoForm;
import br.com.bruno.orange.mercadolivre.validation.ProibeCaracteristicasComNomeIgualValdiator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("produto")
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @InitBinder
    public  void init(WebDataBinder binder){
        binder.addValidators(new ProibeCaracteristicasComNomeIgualValdiator());
    }


    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoDto> salvar(@RequestBody @Valid ProdutoForm form, @AuthenticationPrincipal Usuario usuarioLogado){
        Usuario usuario = usuarioLogado;
        Produto produto = form.toModel(manager, usuario);
        manager.persist(produto);
        return ResponseEntity.ok(new ProdutoDto(produto));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDto>> exibeTodos(){
        List<Produto> produto = manager.createQuery("select u from Produto u").getResultList();
        List<ProdutoDto> dto = produto.stream().map(lista -> new ProdutoDto(lista)).collect(Collectors.toList());
        return ResponseEntity.ok(dto);
    }
}
