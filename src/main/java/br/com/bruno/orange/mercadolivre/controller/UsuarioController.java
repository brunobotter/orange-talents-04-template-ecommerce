package br.com.bruno.orange.mercadolivre.controller;

import br.com.bruno.orange.mercadolivre.model.Usuario;
import br.com.bruno.orange.mercadolivre.model.dto.LoginDao;
import br.com.bruno.orange.mercadolivre.model.form.CadastroForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<LoginDao> cadastrar(@RequestBody @Valid CadastroForm form){
            Usuario usuario = form.toModel();
            manager.persist(usuario);
            return ResponseEntity.ok(new LoginDao(usuario));
    }
}
