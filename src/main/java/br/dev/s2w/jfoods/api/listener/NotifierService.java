package br.dev.s2w.jfoods.api.listener;

import br.dev.s2w.jfoods.api.di.notification.Notifier;
import br.dev.s2w.jfoods.api.di.notification.NotifierType;
import br.dev.s2w.jfoods.api.di.notification.UrgencyLevel;
import br.dev.s2w.jfoods.api.di.service.ActivatedCustomerEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotifierService {

    @Autowired
    @NotifierType(UrgencyLevel.NO_URGENCY)
    private Notifier notifier;

    @EventListener
    public void activatedCustomerListener(ActivatedCustomerEvent event) {
        notifier.notify(event.getCustomer(), "Your registration in the system is active!");
    }

}
