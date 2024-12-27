package br.dev.s2w.jfoods.api.infrastructure.repository;

import br.dev.s2w.jfoods.api.domain.model.Cuisine;
import br.dev.s2w.jfoods.api.domain.repository.CuisineRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CuisineRepositoryImpl implements CuisineRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cuisine> list() {
        return manager.createQuery("from Cuisine", Cuisine.class)
                .getResultList();
    }

    @Override
    public Cuisine search(Long id) {
        return manager.find(Cuisine.class, id);
    }

    @Transactional
    @Override
    public Cuisine save(Cuisine cuisine) {
        return manager.merge(cuisine);
    }

    @Transactional
    @Override
    public void remove(Long cuisineId) {
        Cuisine cuisine = search(cuisineId);

        if (cuisine == null) {
            throw new EmptyResultDataAccessException(1);
        }

        manager.remove(cuisine);
    }

}
