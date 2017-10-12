/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.config;

import br.projeto.estoque.cdm.model.Usuario;
import br.projeto.estoque.cdm.service.UsuarioService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 *
 * 
 */
@Component
public class AuthProviderService implements AuthenticationProvider {

    @Autowired
    private UsuarioService userService;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String login = auth.getName();
        String senha = auth.getCredentials().toString();

        Usuario user = this.userService.pesquisarCredenciais(login, senha);
        senha = null;

        if (user == null) {
            throw new UsernameNotFoundException("Login e/ou Senha inválidos.");
        } else {
            GrantedAuthority autorizacao = new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return user.getPerfilAcesso().getRole();
                }
            };
            ArrayList<GrantedAuthority> autorizacoes = new ArrayList<>();
            autorizacoes.add(autorizacao);

            return new UsernamePasswordAuthenticationToken(user, user.getSenha(), autorizacoes);
        }
    }

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }
}
