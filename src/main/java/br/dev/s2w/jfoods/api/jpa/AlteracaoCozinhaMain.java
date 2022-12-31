package br.dev.s2w.jfoods.api.jpa;

import br.dev.s2w.jfoods.api.S2wJfoodsApiApplication;
import br.dev.s2w.jfoods.api.domain.model.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class AlteracaoCozinhaMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(S2wJfoodsApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);

        Cozinha cozinha = new Cozinha();
        cozinha.setId(1L);
        cozinha.setNome("Brasileira");

        cadastroCozinha.salvar(cozinha);
    }
}
