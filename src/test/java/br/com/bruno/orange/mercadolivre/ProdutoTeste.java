package br.com.bruno.orange.mercadolivre;

import br.com.bruno.orange.mercadolivre.categoria.Categoria;
import br.com.bruno.orange.mercadolivre.usuario.ConverterSenha;
import br.com.bruno.orange.mercadolivre.produto.Produto;
import br.com.bruno.orange.mercadolivre.usuario.Usuario;
import br.com.bruno.orange.mercadolivre.produto.CaracteristicasForm;
import br.com.bruno.orange.mercadolivre.produto.ProdutoForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class ProdutoTeste {

    @DisplayName("produto precisa no minino 3 catacteristicas")
    @ParameterizedTest
    @MethodSource("geradorTestes1")
    void teste1 (Collection<CaracteristicasForm> caracteristicas){
        Categoria categoria = new Categoria("categoria");
        Usuario usuario = new Usuario("admin@gmail.com.br", new ConverterSenha("admin123"));
     new Produto("nome",10, BigDecimal.TEN,"novinho",categoria,usuario,caracteristicas);


    }

    @DisplayName("produto nao pode ser criado com menos de 3 catacteristicas")
    @ParameterizedTest
    @MethodSource("geradorTestes2")
    void teste2 (Collection<CaracteristicasForm> caracteristicas) throws  Exception{
        Categoria categoria = new Categoria("categoria");
        Usuario usuario = new Usuario("admin@gmail.com.br", new ConverterSenha("admin123"));
        Assertions.assertThrows(IllegalArgumentException.class, ()
                -> { new Produto("nome",10, BigDecimal.TEN,"novinho",categoria,usuario,caracteristicas);
        });

    }

    static Stream<Arguments> geradorTestes1(){
        return Stream.of(
                Arguments.of(
                List.of(new CaracteristicasForm("key", "key1"),
                        new CaracteristicasForm("key2", "key2"),
                        new CaracteristicasForm("key3", "key3"))),
                Arguments.of(
                        List.of(new CaracteristicasForm("key", "key1"),
                                new CaracteristicasForm("key2", "key2"),
                                new CaracteristicasForm("key4", "key4"),
                                new CaracteristicasForm("key3", "key3"))));
    }

    static Stream<Arguments> geradorTestes2(){
        return Stream.of(
                Arguments.of(
                        List.of(new CaracteristicasForm("key", "key1"),
                                new CaracteristicasForm("key2", "key2"))),
                Arguments.of(
                        List.of(new CaracteristicasForm("key", "key1")
                                )));
    }

    @DisplayName("cria produto com diversos tipos de caracteristicas")
    @ParameterizedTest
    @MethodSource("geradorTestes3")
    void teste3(int esperado, List<CaracteristicasForm> caracteristicas){
        ProdutoForm form = new ProdutoForm("nome",BigDecimal.TEN,10,"Descricao", 1L, caracteristicas);
    Assertions.assertEquals(esperado, form.buscaCaracteristicasIguais().size());

    }

    private static Stream<Arguments> geradorTestes3(){
        return  Stream.of(Arguments.of(0,List.of()),
                Arguments.of(0,List.of(new CaracteristicasForm("key", "value"))),
                Arguments.of(0,List.of(new CaracteristicasForm("key", "value"),new CaracteristicasForm("key1", "value1"))),
                Arguments.of(1,List.of(new CaracteristicasForm("key", "value"),
                        new CaracteristicasForm("key", "value")))
        );
    }
}
