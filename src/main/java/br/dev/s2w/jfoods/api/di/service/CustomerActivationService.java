package br.dev.s2w.jfoods.api.di.service;

import br.dev.s2w.jfoods.api.di.model.Customer;
import br.dev.s2w.jfoods.api.di.notification.Notifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerActivationService {

    @Autowired(required = false)
    private Notifier notifier;

    public void activate(Customer customer) {
        customer.activate();

        if (notifier != null) {
            notifier.notify(customer, "Your registration in the system is active!");
        } else {
            System.out.println("There is no notifier, but the client has been activated.");
        }
    }
}
