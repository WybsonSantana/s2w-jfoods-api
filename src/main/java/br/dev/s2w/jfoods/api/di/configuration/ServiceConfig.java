package br.dev.s2w.jfoods.api.di.configuration;

import br.dev.s2w.jfoods.api.di.notificacao.Notificador;
import br.dev.s2w.jfoods.api.di.service.AtivacaoClienteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public AtivacaoClienteService ativacaoClienteService(Notificador notificador) {
        return new AtivacaoClienteService(notificador);
    }
}
