package br.dev.s2w.dependency.injection.notification;

import br.dev.s2w.dependency.injection.model.Customer;

public interface Notifier {
    void notify(Customer customer, String message);
}
