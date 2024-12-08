package br.dev.s2w.jfoods.api.di.notification;

import br.dev.s2w.jfoods.api.di.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class EmailNotifier implements Notifier {

    public EmailNotifier() {
        System.out.println("EmailNotifier");
    }

    @Override
    public void notify(Customer customer, String message) {
        System.out.printf("Notifying %s by e-mail %s: %s%n",
                customer.getName(), customer.getEmail(), message);
    }
}
