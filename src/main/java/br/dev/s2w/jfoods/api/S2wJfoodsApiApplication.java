package br.dev.s2w.jfoods.api;

import br.dev.s2w.jfoods.api.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class S2wJfoodsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(S2wJfoodsApiApplication.class, args);
    }

}
