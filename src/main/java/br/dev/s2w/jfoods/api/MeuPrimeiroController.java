package br.dev.s2w.jfoods.api;

import br.dev.s2w.jfoods.api.di.modelo.Cliente;
import br.dev.s2w.jfoods.api.di.service.AtivacaoClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MeuPrimeiroController {

    private AtivacaoClienteService ativacaoClienteService;

    public MeuPrimeiroController(AtivacaoClienteService ativacaoClienteService) {
        this.ativacaoClienteService = ativacaoClienteService;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        Cliente fulano = new Cliente("Fulano de Tal", "fulanodetal@s2w.dev.br", "+55 99 98888-7777");

        ativacaoClienteService.ativar(fulano);

        return "Hello, world!";
    }
}
