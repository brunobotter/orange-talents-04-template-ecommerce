package br.com.bruno.orange.mercadolivre.usuario;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    @Length(min = 6)
    private String senha;

    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataCriacao  = LocalDateTime.now();

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfis = new ArrayList<>();

    @Deprecated
    public Usuario(){

    }

    public Usuario(@Email @NotNull @NotBlank String email,@Valid @NotNull ConverterSenha senhaConverter) {
        this.email = email;
        this.senha = senhaConverter.hash();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
