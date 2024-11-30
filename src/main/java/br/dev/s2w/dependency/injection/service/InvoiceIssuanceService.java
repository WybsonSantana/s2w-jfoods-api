package br.dev.s2w.dependency.injection.service;

import br.dev.s2w.dependency.injection.model.Customer;
import br.dev.s2w.dependency.injection.model.Product;
import br.dev.s2w.dependency.injection.notification.Notifier;

public class InvoiceIssuanceService {

    private Notifier notifier;

    public InvoiceIssuanceService(Notifier notifier) {
        this.notifier = notifier;
    }

    public void issue(Customer customer, Product product) {
        this.notifier.notify(customer, String.format("Invoice for product %s issued successfully!", product.getName()));
    }
}
