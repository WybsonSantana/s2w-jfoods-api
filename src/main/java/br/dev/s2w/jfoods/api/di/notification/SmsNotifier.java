package br.dev.s2w.jfoods.api.di.notification;

import br.dev.s2w.jfoods.api.di.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class SmsNotifier implements Notifier {

    @Override
    public void notify(Customer customer, String message) {
        System.out.printf("Notifying %s via SMS by phone number %s: %s%n",
                customer.getName(), customer.getPhoneNumber(), message);
    }
}
