package br.dev.s2w.jfoods.api.di.service;

import br.dev.s2w.jfoods.api.di.modelo.Cliente;
import br.dev.s2w.jfoods.api.di.notificacao.Notificador;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {

    private Notificador notificador;

    public AtivacaoClienteService(Notificador notificador) {
        this.notificador = notificador;
        System.out.println("AtivacaoClienteService: " + notificador);
    }

    public void ativar(Cliente cliente) {
        cliente.ativar();
        this.notificador.notificar(cliente, "Seu cadastro no sistema agora está ativo!");
    }
}
