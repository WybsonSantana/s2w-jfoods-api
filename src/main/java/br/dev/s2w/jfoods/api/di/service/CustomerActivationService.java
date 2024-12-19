package br.dev.s2w.jfoods.api.di.service;

import br.dev.s2w.jfoods.api.di.model.Customer;
import br.dev.s2w.jfoods.api.di.notification.Notifier;
import br.dev.s2w.jfoods.api.di.notification.NotifierType;
import br.dev.s2w.jfoods.api.di.notification.UrgencyLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerActivationService {

    @Autowired
    @NotifierType(UrgencyLevel.NO_URGENCY)
    private Notifier notifier;

    public void activate(Customer customer) {
        customer.activate();

        notifier.notify(customer, "Your registration in the system is active!");
    }
}
