package br.dev.s2w.jfoods.api.di.service;

import br.dev.s2w.jfoods.api.di.modelo.Cliente;
import br.dev.s2w.jfoods.api.di.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AtivacaoClienteService {

    @Autowired
    private List<Notificador> notificadores;

    public void ativar(Cliente cliente) {
        cliente.ativar();

        for (Notificador notificador : notificadores) {
            notificador.notificar(cliente, "Seu cadastro no sistema agora está ativo!");
        }
    }
}
