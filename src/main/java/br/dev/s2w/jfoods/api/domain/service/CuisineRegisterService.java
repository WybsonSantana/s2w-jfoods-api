package br.dev.s2w.jfoods.api.domain.service;

import br.dev.s2w.jfoods.api.domain.exception.CuisineNotFoundException;
import br.dev.s2w.jfoods.api.domain.exception.EntityInUseException;
import br.dev.s2w.jfoods.api.domain.model.Cuisine;
import br.dev.s2w.jfoods.api.domain.repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CuisineRegisterService {

    private static final String CUISINE_IN_USE_MESSAGE = "The cuisine with code %d cannot be removed because it is in use";

    @Autowired
    private CuisineRepository cuisineRepository;

    public Cuisine find(Long cuisineId) {
        return cuisineRepository.findById(cuisineId)
                .orElseThrow(() -> new CuisineNotFoundException(cuisineId));
    }

    public Cuisine save(Cuisine cuisine) {
        return cuisineRepository.save(cuisine);
    }

    public void remove(Long cuisineId) {
        try {
            cuisineRepository.deleteById(cuisineId);
        } catch (EmptyResultDataAccessException e) {
            throw new CuisineNotFoundException(cuisineId);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(CUISINE_IN_USE_MESSAGE, cuisineId));
        }
    }

}
