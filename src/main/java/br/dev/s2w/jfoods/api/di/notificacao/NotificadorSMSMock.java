package br.dev.s2w.jfoods.api.di.notificacao;

import br.dev.s2w.jfoods.api.di.modelo.Cliente;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@TipoDoNotificador(NivelUrgencia.URGENTE)
@Component
public class NotificadorSMSMock implements Notificador {

    public NotificadorSMSMock() {
        System.out.println("NotificadorSMSMock");
    }

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Deveria notificar %s através do telefone %s: %s\n", cliente.getNome(), cliente.getTelefone(), mensagem);
    }
}
