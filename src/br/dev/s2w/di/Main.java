package br.dev.s2w.di;

import br.dev.s2w.di.modelo.Cliente;
import br.dev.s2w.di.notificacao.Notificador;
import br.dev.s2w.di.notificacao.NotificadorSMS;
import br.dev.s2w.di.service.AtivacaoClienteService;

public class Main {
    public static void main(String[] args) {
        Cliente fulano = new Cliente("Fulano de Tal", "fulanodetal@s2w.dev.br", "+55 99 98888-7777");
        Cliente beltrano = new Cliente("Bel Trano", "beltrano@s2w.dev.br", "+55 88 97777-6666");

        Notificador notificador = new NotificadorSMS();
        
        AtivacaoClienteService ativacaoCliente = new AtivacaoClienteService(notificador);
        ativacaoCliente.ativar(fulano);
        ativacaoCliente.ativar(beltrano);
    }
}