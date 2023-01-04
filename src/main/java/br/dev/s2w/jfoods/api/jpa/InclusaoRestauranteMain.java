package br.dev.s2w.jfoods.api.jpa;

import br.dev.s2w.jfoods.api.S2wJfoodsApiApplication;
import br.dev.s2w.jfoods.api.domain.model.Restaurante;
import br.dev.s2w.jfoods.api.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

public class InclusaoRestauranteMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(S2wJfoodsApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);

        Restaurante restaurante1 = new Restaurante();
        restaurante1.setNome("Byte Foods");
        restaurante1.setTaxafrete(BigDecimal.valueOf(11));

        Restaurante restaurante2 = new Restaurante();
        restaurante2.setNome("Delicious Beans!");
        restaurante2.setTaxafrete(BigDecimal.valueOf(8));

        restaurante1 = restauranteRepository.salvar(restaurante1);
        restaurante2 = restauranteRepository.salvar(restaurante2);

        System.out.printf("%d - %s\n", restaurante1.getId(), restaurante1.getNome());
        System.out.printf("%d - %s\n", restaurante2.getId(), restaurante2.getNome());
    }
}
