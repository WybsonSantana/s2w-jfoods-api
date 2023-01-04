package br.dev.s2w.jfoods.api.jpa;

import br.dev.s2w.jfoods.api.S2wJfoodsApiApplication;
import br.dev.s2w.jfoods.api.domain.model.Restaurante;
import br.dev.s2w.jfoods.api.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ExclusaoRestauranteMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(S2wJfoodsApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);

        Restaurante restaurante = new Restaurante();
        restaurante.setId(1L);

        restauranteRepository.remover(restaurante);
    }
}
