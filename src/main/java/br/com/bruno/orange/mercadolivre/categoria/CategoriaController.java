package br.com.bruno.orange.mercadolivre.categoria;

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
@RequestMapping("/categoria")
public class CategoriaController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaDto> salvar(@RequestBody @Valid CategoriaForm form){
        Categoria categoria = form.toModel(manager);
        manager.persist(categoria);
        return ResponseEntity.ok(new CategoriaDto(categoria));
    }
}
