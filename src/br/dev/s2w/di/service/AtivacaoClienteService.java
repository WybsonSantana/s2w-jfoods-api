package br.dev.s2w.di.service;

import br.dev.s2w.di.modelo.Cliente;
import br.dev.s2w.di.notificacao.Notificador;

public class AtivacaoClienteService {

    private Notificador notificador;

    public AtivacaoClienteService(Notificador notificador) {
        this.notificador = notificador;
    }

    public void ativar(Cliente cliente) {
        cliente.ativar();
        this.notificador.notificar(cliente, "Seu cadastro no sistema agora está ativo!");
    }
}
