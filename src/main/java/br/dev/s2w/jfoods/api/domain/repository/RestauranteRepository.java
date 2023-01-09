package br.dev.s2w.jfoods.api.domain.repository;

import br.dev.s2w.jfoods.api.domain.model.Restaurante;

import java.util.List;

        public interface RestauranteRepository {

    List<Restaurante> listar();

    Restaurante buscar(Long restauranteId);

    Restaurante salvar(Restaurante restaurante);

    void remover(Restaurante restaurante);

}
