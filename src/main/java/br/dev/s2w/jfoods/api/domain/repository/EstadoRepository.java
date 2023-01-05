package br.dev.s2w.jfoods.api.domain.repository;

import br.dev.s2w.jfoods.api.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {

    List<Estado> listar();

    Estado buscar(Long id);

    Estado salvar(Estado estado);

    void remover(Estado estado);

}
