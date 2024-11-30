package br.dev.s2w.dependency.injection;

import br.dev.s2w.dependency.injection.model.Customer;
import br.dev.s2w.dependency.injection.notification.Notifier;
import br.dev.s2w.dependency.injection.notification.SmsNotifier;
import br.dev.s2w.dependency.injection.service.CustomerActivationService;

public class Main {
    public static void main(String[] args) {
        Customer johnDue = new Customer("John Due", "john.due@s2w.com", "+55 11 99897-9695");
        Customer janeDue = new Customer("Jane Due", "jane.due@s2w.com", "+55 11 98786-8584");

        Notifier notifier = new SmsNotifier();

        CustomerActivationService customerActivation = new CustomerActivationService(notifier);

        customerActivation.activate(johnDue);
        customerActivation.activate(janeDue);
    }

}