package br.dev.s2w.jfoods.api.di.listener;

import br.dev.s2w.jfoods.api.di.event.ClienteAtivadoEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmissaoNotaFiscalService {

    @EventListener
    public void clienteAtivadoListener(ClienteAtivadoEvent event) {
        System.out.printf("Emitindo nota fiscal para %s...\n", event.getCliente().getNome());
    }
}
