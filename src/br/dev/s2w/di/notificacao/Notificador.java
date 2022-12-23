package br.dev.s2w.di.notificacao;

import br.dev.s2w.di.modelo.Cliente;

public interface Notificador {
    void notificar(Cliente cliente, String mensagem);
}
