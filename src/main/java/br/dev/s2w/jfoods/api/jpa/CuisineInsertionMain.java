package br.dev.s2w.jfoods.api.jpa;

import br.dev.s2w.jfoods.api.S2wJfoodsApiApplication;
import br.dev.s2w.jfoods.api.domain.model.Cuisine;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class CuisineInsertionMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(S2wJfoodsApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CuisineRegistration cuisineRegistration = applicationContext.getBean(CuisineRegistration.class);

        Cuisine cuisine1 = new Cuisine();
        cuisine1.setName("Brasileira");

        Cuisine cuisine2 = new Cuisine();
        cuisine2.setName("Japonesa");

        cuisine1 = cuisineRegistration.add(cuisine1);
        cuisine2 = cuisineRegistration.add(cuisine2);

        System.out.printf("%s - %s%n", cuisine1.getId(), cuisine1.getName());
        System.out.printf("%s - %s%n", cuisine2.getId(), cuisine2.getName());
    }
}