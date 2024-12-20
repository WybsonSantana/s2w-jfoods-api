package br.dev.s2w.jfoods.api.di.notification;

import br.dev.s2w.jfoods.api.di.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NotifierType(UrgencyLevel.NO_URGENCY)
public class EmailNotifier implements Notifier {

    @Autowired
    private NotifierProperties properties;

    @Override
    public void notify(Customer customer, String message) {
        System.out.printf("Server host: %s%n", properties.getServerHost());
        System.out.printf("Server port: %d%n", properties.getServerPort());

        System.out.printf("Notifying %s by e-mail %s: %s%n",
                customer.getName(), customer.getEmail(), message);
    }
}
