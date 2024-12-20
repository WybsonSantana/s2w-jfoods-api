package br.dev.s2w.jfoods.api.di.service;

import br.dev.s2w.jfoods.api.di.model.Customer;

public class ActivatedCustomerEvent {

    private Customer customer;

    public ActivatedCustomerEvent(Customer customer) {
        super();
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
