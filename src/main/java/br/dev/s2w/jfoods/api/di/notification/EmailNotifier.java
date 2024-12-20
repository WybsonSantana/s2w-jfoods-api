package br.dev.s2w.jfoods.api.di.notification;

import br.dev.s2w.jfoods.api.di.model.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@NotifierType(UrgencyLevel.NO_URGENCY)
public class EmailNotifier implements Notifier {

    @Value("${notifier.email.server-host}")
    private String host;

    @Value("${notifier.email.server-port}")
    private Integer port;

    @Override
    public void notify(Customer customer, String message) {
        System.out.printf("Server host: %s%n", host);
        System.out.printf("Server port: %d%n", port);

        System.out.printf("Notifying %s by e-mail %s: %s%n",
                customer.getName(), customer.getEmail(), message);
    }
}
