package br.dev.s2w.jfoods.api.domain.repository;

import br.dev.s2w.jfoods.api.domain.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {

    List<Cozinha> listar();

    Cozinha buscar(Long cozinhaId);

    Cozinha salvar(Cozinha cozinha);

    void remover(Long cozinhaId);

}
