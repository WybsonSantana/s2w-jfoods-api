package br.dev.s2w.jfoods.api.domain.repository;

import br.dev.s2w.jfoods.api.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends CustomJpaRepository<Restaurant, Long>,
        RestaurantRepositoryQueries, JpaSpecificationExecutor<Restaurant> {
    List<Restaurant> findByDeliveryFeeBetween(BigDecimal initialFee, BigDecimal finalFee);

    //@Query("from Restaurant where name like %:name% and cuisine.id = :cuisineId")
    List<Restaurant> queryByName(String name, @Param("cuisineId") Long cuisineId);

    //List<Restaurant> findByNameContainingAndCuisineId(String name, Long cuisineId);

    Optional<Restaurant> getFirstRestaurantByNameContaining(String name);

    List<Restaurant> queryTop2ByNameContaining(String name);

    int countByCuisineId(Long cuisineId);
}