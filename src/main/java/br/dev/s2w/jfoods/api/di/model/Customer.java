package br.dev.s2w.jfoods.api.di.model;

public class Customer {

    private String name;
    private String email;
    private String phoneNumber;
    private boolean activated = false;

    public Customer(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isActivated() {
        return activated;
    }

    public void activate() {
        this.activated = true;
    }
}
