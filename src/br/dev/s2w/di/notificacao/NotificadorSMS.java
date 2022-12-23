package br.dev.s2w.di.notificacao;

import br.dev.s2w.di.modelo.Cliente;

public class NotificadorSMS implements Notificador{

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Notificando %s através do telefone %s: %s\n", cliente.getNome(), cliente.getTelefone(), mensagem);
    }
}
