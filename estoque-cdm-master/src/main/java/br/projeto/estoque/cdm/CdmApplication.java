package br.projeto.estoque.cdm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.projeto.estoque.cdm.model.PerfilAcesso;
import br.projeto.estoque.cdm.model.Unidade;
import br.projeto.estoque.cdm.model.Usuario;
import br.projeto.estoque.cdm.service.PerfilAcessoService;
import br.projeto.estoque.cdm.service.UnidadeService;
import br.projeto.estoque.cdm.service.UsuarioService;

//@SpringBootApplication
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class CdmApplication extends SpringBootServletInitializer{

    @Autowired
    UsuarioService service;

    @Autowired
    PerfilAcessoService perfilAcesso;

    @Autowired
    UnidadeService unidadeService;

    public static void main(String[] args) {
        SpringApplication.run(CdmApplication.class, args);
    }

    @Bean
    CommandLineRunner init() {
        return (args) -> {

            PerfilAcesso p = new PerfilAcesso();
            Unidade u = new Unidade();

            List<PerfilAcesso> perfils = this.perfilAcesso.buscarTodos();
            if (perfils == null || perfils.isEmpty()) {
                p.setAdm(true);
                p.setDescricao("Teste");
                p = this.perfilAcesso.salvarOuAtualizar(p);
            }

            List<Unidade> unidades = this.unidadeService.buscarTodos();
            if (unidades == null || unidades.isEmpty()) {
                u.setAtivo(true);
                u.setCnpj("04292234000100");
                u.setNome("Teste");
                u.setPedidoEspecial(true);
                u = this.unidadeService.salvarOuAtualizar(u);
            }

            List<Usuario> usuarios = this.service.buscarTodos();
            if (usuarios == null || usuarios.isEmpty()) {
                Usuario user = new Usuario("ROOT", p, u, "root", "root");
                this.service.salvarOuAtualizar(user);
            }

        };
    }
}
