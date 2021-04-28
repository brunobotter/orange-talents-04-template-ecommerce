package br.com.bruno.orange.mercadolivre.security;

import br.com.bruno.orange.mercadolivre.model.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class AutenticacaoService implements UserDetailsService {

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
