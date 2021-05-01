package br.com.bruno.orange.mercadolivre.pergunta;

import br.com.bruno.orange.mercadolivre.produto.Produto;
import br.com.bruno.orange.mercadolivre.usuario.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping
public class PerguntaController {


    @PersistenceContext
    private EntityManager manager;

    @PostMapping("produto/{id}/pergunta")
    @Transactional
    public ResponseEntity<PerguntaDto> pergunta
            (@PathVariable("id") Long id, @Valid @RequestBody PerguntaForm form,
                                                @AuthenticationPrincipal Usuario usuarioLogado){
        Usuario usuario = usuarioLogado;
        Produto produto = manager.find(Produto.class, id);
        if(!produto.pertenceAoUsuario(usuarioLogado)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        Pergunta pergunta = form.toModel(usuario, produto);
        manager.persist(pergunta);
        return ResponseEntity.ok(new PerguntaDto(pergunta));
    }
}
