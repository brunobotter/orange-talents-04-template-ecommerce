package br.com.bruno.orange.mercadolivre.controller;

import br.com.bruno.orange.mercadolivre.model.Opniao;
import br.com.bruno.orange.mercadolivre.model.Produto;
import br.com.bruno.orange.mercadolivre.model.Usuario;
import br.com.bruno.orange.mercadolivre.model.dto.OpniaoDto;
import br.com.bruno.orange.mercadolivre.model.form.OpniaoForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping
public class OpniaoController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("produto/{id}/opniao")
    @Transactional
    public ResponseEntity<OpniaoDto> opniao(@PathVariable("id") Long id
            , @Valid @RequestBody OpniaoForm form
            , @AuthenticationPrincipal Usuario usuarioLogado){
        Usuario usuario = usuarioLogado;
        Produto produto = manager.find(Produto.class, id);
        if(!produto.pertenceAoUsuario(usuarioLogado)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        Opniao opniao = form.toModel(produto, usuario);
        manager.persist(opniao);
        return ResponseEntity.ok(new OpniaoDto(opniao));
    }
}
