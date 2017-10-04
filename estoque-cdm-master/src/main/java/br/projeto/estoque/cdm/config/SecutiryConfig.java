/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.config;

import br.projeto.estoque.cdm.model.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * 
 */
@EnableWebSecurity
public class SecutiryConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthProviderService authProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("master").password("1").roles("USER").and()
//                .withUser("root").password("root").roles("ROOT");
        auth.authenticationProvider(authProvider);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // PERMITINDO ACESSO AOS LAYOUTs PARA FUNCIONAR
        web.ignoring()
                .antMatchers("/css/**")
                .and()
                .ignoring()
                .antMatchers("/favicon/**")
                .and()
                .ignoring()
                .antMatchers("/fonts/**")
                .and()
                .ignoring()
                .antMatchers("/imagens/**")
                .and()
                .ignoring()
                .antMatchers("/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/usuario").hasAuthority(Authority.ROOT.name())
                .antMatchers("/perfilAcesso").hasAuthority(Authority.ROOT.name())
                .antMatchers("/faixaatendimento").hasAuthority(Authority.ROOT.name())
                .antMatchers("/unidade").hasAuthority(Authority.ROOT.name())
                .antMatchers("/endereco").hasAuthority(Authority.ROOT.name())
                .antMatchers("/numero").hasAuthority(Authority.ROOT.name())
                .antMatchers("/cidade").hasAuthority(Authority.ROOT.name())
                .antMatchers("/estado").hasAuthority(Authority.ROOT.name())
                .antMatchers("/cep").hasAuthority(Authority.ROOT.name())
                .antMatchers("/contato").hasAuthority(Authority.ROOT.name())
                .antMatchers("/telefone").hasAuthority(Authority.ROOT.name())
                .antMatchers("/email").hasAuthority(Authority.ROOT.name())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll();
    }
}
