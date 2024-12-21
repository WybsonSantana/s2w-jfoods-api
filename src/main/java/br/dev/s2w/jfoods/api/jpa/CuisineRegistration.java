package br.dev.s2w.jfoods.api.jpa;

import br.dev.s2w.jfoods.api.domain.model.Cuisine;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CuisineRegistration {

    @PersistenceContext
    private EntityManager manager;

    public List<Cuisine> list() {
        return manager.createQuery("from Cuisine", Cuisine.class)
                .getResultList();
    }

    public Cuisine search(Long id) {
        return manager.find(Cuisine.class, id);
    }

    @Transactional
    public Cuisine save(Cuisine cuisine) {
        return manager.merge(cuisine);
    }
}
