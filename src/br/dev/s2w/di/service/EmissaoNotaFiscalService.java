package br.dev.s2w.di.service;

import br.dev.s2w.di.modelo.Cliente;
import br.dev.s2w.di.modelo.Produto;
import br.dev.s2w.di.notificacao.Notificador;

public class EmissaoNotaFiscalService {

    private Notificador notificador;

    public EmissaoNotaFiscalService(Notificador notificador) {
        this.notificador = notificador;
    }

    public void emitir(Cliente cliente, Produto produto) {
        System.out.println("Emissão da nota fiscal...");
this.notificador.notificar(cliente, "A nota fiscal do produto " + produto.getNome() + " foi emitida!");
    }
}
