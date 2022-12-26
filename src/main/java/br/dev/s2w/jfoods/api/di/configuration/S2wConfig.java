package br.dev.s2w.jfoods.api.di.configuration;

import br.dev.s2w.jfoods.api.di.notificacao.NotificadorEmail;
import br.dev.s2w.jfoods.api.di.service.AtivacaoClienteService;
import org.springframework.context.annotation.Bean;

//@Configuration
public class S2wConfig {

    @Bean
    public NotificadorEmail notificadorEmail() {
        NotificadorEmail notificador = new NotificadorEmail("smtp@s2w.dev.br");
        notificador.setCaixaAlta(true);

        return notificador;
    }

    @Bean
    public AtivacaoClienteService ativacaoClienteService() {
        return new AtivacaoClienteService(notificadorEmail());
    }
}
