package br.dev.s2w.jfoods.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MeuPrimeiroController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello, world!";
    }
}
