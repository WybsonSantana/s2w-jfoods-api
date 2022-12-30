package br.dev.s2w.jfoods.api.di.listener;

import br.dev.s2w.jfoods.api.di.event.ClienteAtivadoEvent;
import br.dev.s2w.jfoods.api.di.notificacao.NivelUrgencia;
import br.dev.s2w.jfoods.api.di.notificacao.Notificador;
import br.dev.s2w.jfoods.api.di.notificacao.TipoDoNotificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoService {

    @TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
    @Autowired
    private Notificador notificador;

    @EventListener
    public void clienteAtivadoListener(ClienteAtivadoEvent event) {
        notificador.notificar(event.getCliente(), "Seu cadastro no sistema agora está ativo!");
    }
}
