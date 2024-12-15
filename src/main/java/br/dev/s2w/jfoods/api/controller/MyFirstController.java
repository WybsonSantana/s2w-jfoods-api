package br.dev.s2w.jfoods.api.controller;

import br.dev.s2w.jfoods.api.di.model.Customer;
import br.dev.s2w.jfoods.api.di.service.CustomerActivationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyFirstController {

    private CustomerActivationService customerActivationService;

    public MyFirstController(CustomerActivationService customerActivationService) {
        this.customerActivationService = customerActivationService;

        System.out.printf("MyFirstController: %s%n", customerActivationService);
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        Customer john = new Customer("John Due", "john.due@s2w.dev.br", "+1 321 555-6677");

        customerActivationService.activate(john);

        return "Hello, JFoods!";
    }
}
