package br.dev.s2w.jfoods.api.domain.repository;

import br.dev.s2w.jfoods.api.domain.model.City;

import java.util.List;

public interface CityRepository {
    List<City> list();

    City search(Long id);

    City save(City city);

    void remove(City city);
}
