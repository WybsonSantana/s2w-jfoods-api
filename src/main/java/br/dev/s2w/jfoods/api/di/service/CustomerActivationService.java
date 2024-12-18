package br.dev.s2w.jfoods.api.di.service;

import br.dev.s2w.jfoods.api.di.model.Customer;
import br.dev.s2w.jfoods.api.di.notification.Notifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerActivationService {

    @Autowired
    private Notifier notifier;

//    @Autowired
//    public CustomerActivationService(Notifier notifier) {
//        this.notifier = notifier;
//    }
//
//    public CustomerActivationService(String any) {
//
//    }

    public void activate(Customer customer) {
        customer.activate();
        notifier.notify(customer, "Your registration in the system is active!");
    }

//    @Autowired
//    public void setNotifier(Notifier notifier) {
//        this.notifier = notifier;
//    }
}
