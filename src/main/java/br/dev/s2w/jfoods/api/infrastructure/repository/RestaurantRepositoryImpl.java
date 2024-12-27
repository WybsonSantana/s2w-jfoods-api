package br.dev.s2w.jfoods.api.infrastructure.repository;

import br.dev.s2w.jfoods.api.domain.model.Restaurant;
import br.dev.s2w.jfoods.api.domain.repository.RestaurantRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurant> list() {
        return manager.createQuery("from Restaurant", Restaurant.class)
                .getResultList();
    }

    @Override
    public Restaurant search(Long id) {
        return manager.find(Restaurant.class, id);
    }

    @Transactional
    @Override
    public Restaurant save(Restaurant restaurant) {
        return manager.merge(restaurant);
    }

    @Transactional
    @Override
    public void remove(Restaurant restaurant) {
        restaurant = search(restaurant.getId());
        manager.remove(restaurant);
    }

}
