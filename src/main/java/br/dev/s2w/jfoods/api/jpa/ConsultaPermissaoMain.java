package br.dev.s2w.jfoods.api.jpa;

import br.dev.s2w.jfoods.api.S2wJfoodsApiApplication;
import br.dev.s2w.jfoods.api.domain.model.Permissao;
import br.dev.s2w.jfoods.api.domain.repository.PermissaoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultaPermissaoMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(S2wJfoodsApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        PermissaoRepository permissaoRepository = applicationContext.getBean(PermissaoRepository.class);

        List<Permissao> permissoes = permissaoRepository.listar();

        for (Permissao permissao : permissoes) {
            System.out.printf("%s - %s\n", permissao.getNome(), permissao.getDescricao());
        }
    }
}
