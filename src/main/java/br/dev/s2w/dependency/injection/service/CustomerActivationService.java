package br.dev.s2w.dependency.injection.service;

import br.dev.s2w.dependency.injection.model.Customer;
import br.dev.s2w.dependency.injection.notification.Notifier;

public class CustomerActivationService {

    private Notifier notifier;

    public CustomerActivationService(Notifier notifier) {
        this.notifier = notifier;
    }

    public void activate(Customer customer) {
        customer.activate();

        this.notifier.notify(customer, "Your registration in the system is active!");
    }
}
