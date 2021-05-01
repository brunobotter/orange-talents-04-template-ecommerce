package br.com.bruno.orange.mercadolivre.seguranca;

import br.com.bruno.orange.mercadolivre.usuario.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class AutenticacaoManager implements UserDetailsService {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Query query = manager.createQuery("select u from Usuario u where u.email = :username");
        List<Usuario> usuario = query.setParameter("username", username).getResultList();
        if(usuario.isEmpty()){
            throw new UsernameNotFoundException("Usuario nao encontrado");
        }
        return usuario.get(0);
    }


    public Usuario findById(Long idUsuario) {
        Query query = manager.createQuery("select u from Usuario u where u.id = :idUsuario");
        List<Usuario> usuario = query.setParameter("idUsuario", idUsuario).getResultList();
        if(usuario.isEmpty()){
            throw new UsernameNotFoundException("Usuario nao encontrado");
        }
        return usuario.get(0);
    }
}
