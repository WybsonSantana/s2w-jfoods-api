package br.dev.s2w.jfoods.api.di.service;

import br.dev.s2w.jfoods.api.di.model.Customer;
import br.dev.s2w.jfoods.api.di.notification.EmailNotifier;
import org.springframework.stereotype.Component;

@Component
public class CustomerActivationService {

    private EmailNotifier notifier;

    public void activate(Customer customer) {
        customer.activate();

        this.notifier.notify(customer, "Your registration in the system is active!");
    }
}
