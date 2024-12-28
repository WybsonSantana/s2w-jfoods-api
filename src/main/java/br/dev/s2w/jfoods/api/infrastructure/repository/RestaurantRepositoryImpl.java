package br.dev.s2w.jfoods.api.infrastructure.repository;

import br.dev.s2w.jfoods.api.domain.model.Restaurant;
import br.dev.s2w.jfoods.api.domain.repository.RestaurantRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurant> find(String name, BigDecimal initialFee, BigDecimal finalFee) {
        var jpql = new StringBuilder();
        var parameters = new HashMap<String, Object>();

        jpql.append("from Restaurant where 0 = 0 ");

        if (StringUtils.hasLength(name)) {
            jpql.append("and name like :name ");
            parameters.put("name", "%" + name + "%");
        }

        if (initialFee != null) {
            jpql.append("and deliveryFee >= :initialFee ");
            parameters.put("initialFee", initialFee);
        }

        if (finalFee != null) {
            jpql.append("and deliveryFee <= :finalFee ");
            parameters.put("finalFee", finalFee);
        }

        TypedQuery<Restaurant> query = manager.createQuery(jpql.toString(), Restaurant.class);

        parameters.forEach((key, value) ->
                query.setParameter(key, value)
        );

        return query.getResultList();
    }

}
