package br.dev.s2w.jfoods.api.di.notification;

import br.dev.s2w.jfoods.api.di.model.Customer;

public interface Notifier {
    void notify(Customer customer, String message);
}
