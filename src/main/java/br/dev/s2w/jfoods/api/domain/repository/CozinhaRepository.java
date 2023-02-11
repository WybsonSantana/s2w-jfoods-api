package br.dev.s2w.jfoods.api.domain.repository;

import br.dev.s2w.jfoods.api.domain.model.Cozinha;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CozinhaRepository {

    List<Cozinha> listar();

    List<Cozinha> consultarPorNome(String nome);

    Cozinha buscar(Long cozinhaId);

    Cozinha salvar(Cozinha cozinha);

    void remover(Long cozinhaId);

}
