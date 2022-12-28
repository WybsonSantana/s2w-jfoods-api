package br.dev.s2w.jfoods.api.di.service;

import br.dev.s2w.jfoods.api.di.modelo.Cliente;
import br.dev.s2w.jfoods.api.di.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {

    @Autowired
    private Notificador notificador;

    public void ativar(Cliente cliente) {
        cliente.ativar();

        notificador.notificar(cliente, "Seu cadastro no sistema agora está ativo!");
    }
}
