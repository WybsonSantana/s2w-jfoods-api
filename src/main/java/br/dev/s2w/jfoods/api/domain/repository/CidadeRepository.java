package br.dev.s2w.jfoods.api.domain.repository;

import br.dev.s2w.jfoods.api.domain.model.Cidade;

import java.util.List;

public interface CidadeRepository {

    List<Cidade> listar();

    Cidade buscar(Long id);

    Cidade salvar(Cidade cidade);

    void remover(Cidade cidade);

}
