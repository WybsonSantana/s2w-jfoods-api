package br.dev.s2w.jfoods.api.di.service;

import br.dev.s2w.jfoods.api.di.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CustomerActivationService {

    @Autowired
    public ApplicationEventPublisher eventPublisher;

    public void activate(Customer customer) {
        customer.activate();

        eventPublisher.publishEvent(new ActivatedCustomerEvent(customer));
    }
}
