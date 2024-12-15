package br.dev.s2w.jfoods.api.di.notification;

import br.dev.s2w.jfoods.api.di.model.Customer;

public class EmailNotifier implements Notifier {

    private boolean uppercase;
    private String hostSmtpServer;

    public EmailNotifier(String hostSmtpServer) {
        this.hostSmtpServer = hostSmtpServer;
        System.out.println("EmailNotifier");
    }

    @Override
    public void notify(Customer customer, String message) {
        if (this.uppercase) {
            message = message.toUpperCase();
        }

        System.out.printf("Notifying %s by e-mail %s: %s%n",
                customer.getName(), customer.getEmail(), message);
    }

    public void setUppercase(boolean uppercase) {
        this.uppercase = uppercase;
    }
}
