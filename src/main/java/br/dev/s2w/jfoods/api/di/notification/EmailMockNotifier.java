package br.dev.s2w.jfoods.api.di.notification;

import br.dev.s2w.jfoods.api.di.model.Customer;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@NotifierType(UrgencyLevel.NO_URGENCY)
@Profile("dev")
public class EmailMockNotifier implements Notifier {

    public EmailMockNotifier() {
        System.out.println("Mock EmailNotifier");
    }

    @Override
    public void notify(Customer customer, String message) {
        System.out.printf("Notifying %s by e-mail %s: %s%n",
                customer.getName(), customer.getEmail(), message);
    }
}