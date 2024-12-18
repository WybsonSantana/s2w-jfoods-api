package br.dev.s2w.jfoods.api.di.service;

import br.dev.s2w.jfoods.api.di.model.Customer;
import br.dev.s2w.jfoods.api.di.notification.Notifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerActivationService {

    @Autowired
    private List<Notifier> notifiers;

    public void activate(Customer customer) {
        customer.activate();

        for (Notifier notifier : notifiers) {
            notifier.notify(customer, "Your registration in the system is active!");
        }
    }
}
