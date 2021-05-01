package br.com.bruno.orange.mercadolivre.produto;

import br.com.bruno.orange.mercadolivre.upload.UploadFake;
import br.com.bruno.orange.mercadolivre.usuario.Usuario;
import br.com.bruno.orange.mercadolivre.imagem.ImagemForm;
import br.com.bruno.orange.mercadolivre.validacao.ProibeCaracteristicasComNomeIgualValdiator;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping()
public class ProdutoController {
    @Autowired
    private UploadFake uploadFake;

    @PersistenceContext
    private EntityManager manager;

    @InitBinder(value = "ProdutoForm")
    public  void init(WebDataBinder binder){
        binder.addValidators(new ProibeCaracteristicasComNomeIgualValdiator());
    }


    @PostMapping("produto")
    @Transactional
    public ResponseEntity<ProdutoDto> salvar(@RequestBody @Valid ProdutoForm form, @AuthenticationPrincipal Usuario usuarioLogado){
        Usuario usuario = usuarioLogado;
        Produto produto = form.toModel(manager, usuario);
        manager.persist(produto);
        return ResponseEntity.ok(new ProdutoDto(produto));
    }


    @PostMapping("produto/{id}/imagem")
    @Transactional
    public void adicionaImagens(@PathVariable("id") Long id, @Valid ImagemForm form, @AuthenticationPrincipal Usuario usuarioLogado){
    /*
    * 1 - enviar imagens para o local onde eles vao ficar
    * 2 - pergar os links de todas as imagens
    * 3 - associar esses links com o produto em questao
    * 4 - preciso carregar o produto
    * 5 - depois que associar preciso atualizar a nova versao do produto
    * */
        Usuario usuario = usuarioLogado;
        Set<String> links = uploadFake.envia(form.getImagens());
        Produto produto = manager.find(Produto.class, id);
        if(!produto.pertenceAoUsuario(usuarioLogado)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        produto.associaImagens(links);
        manager.merge(produto);
    }


    @GetMapping("produto/{id}")
    public ResponseEntity<DetalhesProdutoDto> detalhaProdutos(@PathVariable("id")Long id){
        Produto produto = (Produto) manager.createQuery("select u from Produto u where u.id = :id").setParameter("id", id).getSingleResult();
        return ResponseEntity.ok(new DetalhesProdutoDto(produto));
    }

    @GetMapping("produto")
    public ResponseEntity<List<ProdutoDto>> exibeTodos(){
        List<Produto> produto = manager.createQuery("select u from Produto u").getResultList();
        List<ProdutoDto> dto = produto.stream().map(lista -> new ProdutoDto(lista)).collect(Collectors.toList());
        return ResponseEntity.ok(dto);
    }


}
