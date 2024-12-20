package br.dev.s2w.jfoods.api.listener;

import br.dev.s2w.jfoods.api.di.service.ActivatedCustomerEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InvoiceIssuingService {

    @EventListener
    public void activatedCustomerListener(ActivatedCustomerEvent event) {
        System.out.printf("Issuing invoice to the customer %s...%n", event.getCustomer().getName());
    }
}
