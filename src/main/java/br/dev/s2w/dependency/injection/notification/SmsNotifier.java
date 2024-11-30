package br.dev.s2w.dependency.injection.notification;

import br.dev.s2w.dependency.injection.model.Customer;

public class SmsNotifier implements Notifier {

    @Override
    public void notify(Customer customer, String message) {
        System.out.printf("Notifying %s by SMS via phone number %s: %s%n",
                customer.getName(), customer.getPhoneNumber(), message);
    }
}
