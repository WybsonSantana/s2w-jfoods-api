package br.dev.s2w.jfoods.api.configuration;

import br.dev.s2w.jfoods.api.di.notification.Notifier;
import br.dev.s2w.jfoods.api.di.service.CustomerActivationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public CustomerActivationService customerActivationService(Notifier notifier) {
        return new CustomerActivationService(notifier);
    }
}
