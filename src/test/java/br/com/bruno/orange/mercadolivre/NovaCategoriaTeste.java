package br.com.bruno.orange.mercadolivre;

import br.com.bruno.orange.mercadolivre.categoria.Categoria;
import br.com.bruno.orange.mercadolivre.categoria.CategoriaForm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;

public class NovaCategoriaTeste {


    @Test
    @DisplayName("Deveria cadastrar uma categoria sem mae")
    void teste1() throws Exception{
        CategoriaForm categoria = new CategoriaForm();
        categoria.setNome("Livro");
        EntityManager manager = Mockito.mock(EntityManager.class);
        categoria.toModel(manager);
        Mockito.verify(manager, Mockito.never())
                .find(Mockito.eq(Categoria.class), Mockito.anyLong());
    }

    @Test
    @DisplayName("Deveria cadastrar uma categoria com mae")
    void teste2() throws Exception{
        CategoriaForm categoria = new CategoriaForm();
        categoria.setNome("Livro");
        categoria.setIdCategoriaMae(10L);
        EntityManager manager = Mockito.mock(EntityManager.class);
        Categoria categoriaMae = new Categoria("Teste");
        Mockito.when(manager.find(Categoria.class,10L)).thenReturn(categoriaMae);
        categoria.toModel(manager);
        Mockito.verify(manager).find(Categoria.class, 10L);
    }
}
