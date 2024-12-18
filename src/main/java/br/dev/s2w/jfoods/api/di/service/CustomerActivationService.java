package br.dev.s2w.jfoods.api.di.service;

import br.dev.s2w.jfoods.api.di.model.Customer;
import br.dev.s2w.jfoods.api.di.notification.Notifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CustomerActivationService {

    @Autowired
    @Qualifier("urgent")
    private Notifier notifier;

    public void activate(Customer customer) {
        customer.activate();

        notifier.notify(customer, "Your registration in the system is active!");
    }
}
