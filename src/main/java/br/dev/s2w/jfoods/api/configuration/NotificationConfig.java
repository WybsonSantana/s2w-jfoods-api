package br.dev.s2w.jfoods.api.configuration;

import br.dev.s2w.jfoods.api.di.notification.EmailNotifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig {

    @Bean
    public EmailNotifier emailNotifier() {
        EmailNotifier notifier = new EmailNotifier("smtp.s2w.dev.br");
        notifier.setUppercase(true);

        return notifier;

    }
}
