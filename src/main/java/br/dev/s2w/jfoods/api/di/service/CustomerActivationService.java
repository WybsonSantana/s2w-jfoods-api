package br.dev.s2w.jfoods.api.di.service;

import br.dev.s2w.jfoods.api.di.model.Customer;
import br.dev.s2w.jfoods.api.di.notification.Notifier;
import br.dev.s2w.jfoods.api.di.notification.NotifierType;
import br.dev.s2w.jfoods.api.di.notification.UrgencyLevel;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Component
public class CustomerActivationService {

    @Autowired
    @NotifierType(UrgencyLevel.NO_URGENCY)
    private Notifier notifier;

    @PostConstruct
    public void init() {
        System.out.println("CustomerActivationService instance created!");
        System.out.printf("INIT %s%n", notifier);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("DESTROY");
    }

    public void activate(Customer customer) {
        customer.activate();

        notifier.notify(customer, "Your registration in the system is active!");
    }
}
