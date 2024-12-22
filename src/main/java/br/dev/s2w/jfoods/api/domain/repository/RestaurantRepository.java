package br.dev.s2w.jfoods.api.domain.repository;

import br.dev.s2w.jfoods.api.domain.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    List<Restaurant> list();

    Restaurant search(Long id);

    Restaurant save(Restaurant restaurant);

    void remove(Restaurant restaurant);
}
