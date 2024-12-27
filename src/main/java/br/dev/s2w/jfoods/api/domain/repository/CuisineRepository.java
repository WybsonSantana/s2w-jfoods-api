package br.dev.s2w.jfoods.api.domain.repository;

import br.dev.s2w.jfoods.api.domain.model.Cuisine;

import java.util.List;

public interface CuisineRepository {
    List<Cuisine> list();

    List<Cuisine> findByName(String name);

    Cuisine search(Long id);

    Cuisine save(Cuisine cuisine);

    void remove(Long cuisineId);
}