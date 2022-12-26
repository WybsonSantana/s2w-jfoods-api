package br.dev.s2w.jfoods.api.di.configuration;

import br.dev.s2w.jfoods.api.di.notificacao.NotificadorEmail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificacaoConfig {

    @Bean
    public NotificadorEmail notificadorEmail() {
        NotificadorEmail notificador = new NotificadorEmail("smtp@s2w.dev.br");
        notificador.setCaixaAlta(true);

        return notificador;
    }
}
