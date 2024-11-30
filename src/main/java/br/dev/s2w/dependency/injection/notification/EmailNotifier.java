package br.dev.s2w.dependency.injection.notification;

import br.dev.s2w.dependency.injection.model.Customer;

public class EmailNotifier implements Notifier {

    @Override
    public void notify(Customer customer, String message) {
        System.out.printf("Notifying %s by e-mail %s: %s%n",
                customer.getName(), customer.getEmail(), message);
    }
}
