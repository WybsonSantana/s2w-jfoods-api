package br.dev.s2w.jfoods.api.di.service;

import br.dev.s2w.jfoods.api.di.model.Customer;
import br.dev.s2w.jfoods.api.di.notification.Notifier;
import org.springframework.stereotype.Component;

@Component
public class CustomerActivationService {

    private Notifier notifier;

    public CustomerActivationService(Notifier notifier) {
        this.notifier = notifier;

        System.out.printf("CustomerActivationService: %s%n", notifier);
    }

    public void activate(Customer customer) {
        customer.activate();

        notifier.notify(customer, "Your registration in the system is active!");
    }
}
