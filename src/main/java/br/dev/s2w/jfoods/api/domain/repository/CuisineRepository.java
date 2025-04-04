package br.dev.s2w.jfoods.api.domain.repository;

import br.dev.s2w.jfoods.api.domain.model.Cuisine;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuisineRepository extends CustomJpaRepository<Cuisine, Long> {

    List<Cuisine> findAllByNameContaining(String name);

    Optional<Cuisine> findByName(String name);

    boolean existsByName(String name);

}
