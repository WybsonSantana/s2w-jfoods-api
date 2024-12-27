package br.dev.s2w.jfoods.api.infrastructure.repository;

import br.dev.s2w.jfoods.api.domain.model.City;
import br.dev.s2w.jfoods.api.domain.repository.CityRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CityRepositoryImpl implements CityRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<City> list() {
        return manager.createQuery("from City", City.class)
                .getResultList();
    }

    @Override
    public City search(Long id) {
        return manager.find(City.class, id);
    }

    @Transactional
    @Override
    public City save(City city) {
        return manager.merge(city);
    }

    @Transactional
    @Override
    public void remove(Long cityId) {
        City city = search(cityId);

        if (city == null) {
            throw new EmptyResultDataAccessException(1);
        }

        manager.remove(city);
    }

}
