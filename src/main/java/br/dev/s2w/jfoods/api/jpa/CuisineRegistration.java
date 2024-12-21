package br.dev.s2w.jfoods.api.jpa;

import br.dev.s2w.jfoods.api.domain.model.Cuisine;
import org.springframework.stereotype.Component;

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
}
