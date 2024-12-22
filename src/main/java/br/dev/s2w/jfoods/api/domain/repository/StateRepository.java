package br.dev.s2w.jfoods.api.domain.repository;

import br.dev.s2w.jfoods.api.domain.model.State;

import java.util.List;

public interface StateRepository {
    List<State> list();

    State search(Long id);

    State save(State state);

    void remove(State state);
}
