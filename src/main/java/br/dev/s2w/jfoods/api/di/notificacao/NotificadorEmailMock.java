package br.dev.s2w.jfoods.api.di.notificacao;

import br.dev.s2w.jfoods.api.di.modelo.Cliente;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmailMock implements Notificador {

    public NotificadorEmailMock() {
        System.out.println("NotificadorEmailMock");
    }

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Deveria notificar %s através do email %s: %s\n", cliente.getNome(), cliente.getEmail(), mensagem);
    }
}
