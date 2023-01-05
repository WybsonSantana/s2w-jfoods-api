package br.dev.s2w.jfoods.api.jpa;

import br.dev.s2w.jfoods.api.S2wJfoodsApiApplication;
import br.dev.s2w.jfoods.api.domain.model.Estado;
import br.dev.s2w.jfoods.api.domain.repository.EstadoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultaEstadoMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(S2wJfoodsApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);

        List<Estado> estados = estadoRepository.listar();

        for (Estado estado : estados) {
            System.out.println(estado.getNome());
        }
    }
}
