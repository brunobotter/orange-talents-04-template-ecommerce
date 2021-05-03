package br.com.bruno.orange.mercadolivre.compra;

import br.com.bruno.orange.mercadolivre.email.Email;
import br.com.bruno.orange.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RestController
@RequestMapping()
public class CompraController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private Email email;


    @PostMapping("compra")
    @Transactional
    public ResponseEntity<CompraDto> salvar(@RequestBody CompraForm form,
                                            @AuthenticationPrincipal Usuario usuarioLogado, UriComponentsBuilder uri) throws Exception {
        Compra compra = form.toModel(usuarioLogado, manager);
        manager.persist(compra);
        email.novaCompra(compra);
        if(compra.getGateway().equals(Gateway.pagseguro)){
            String url = "/pagseguro.com/"+compra.getId()+"&redirectUrl="+uri.path("/pagseguro.com?returnId={id}").buildAndExpand(compra.getId()).toString();
            return ResponseEntity.ok(new CompraDto(compra,url));
        }else{
            String url = "/paypal.com/"+compra.getId()+"&redirectUrl="+uri.path("/paypal.com?returnId={id}").buildAndExpand(compra.getId()).toString();
            return ResponseEntity.ok(new CompraDto(compra,url));
        }
    }
}
