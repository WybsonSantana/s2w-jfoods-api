package br.dev.s2w.jfoods.api.di.notificacao;

import br.dev.s2w.jfoods.api.di.modelo.Cliente;

public interface Notificador {
    void notificar(Cliente cliente, String mensagem);
}
