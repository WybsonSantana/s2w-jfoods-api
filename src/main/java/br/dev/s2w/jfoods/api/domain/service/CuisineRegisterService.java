package br.dev.s2w.jfoods.api.domain.service;

import br.dev.s2w.jfoods.api.domain.exception.EntityInUseException;
import br.dev.s2w.jfoods.api.domain.exception.EntityNotFoundException;
import br.dev.s2w.jfoods.api.domain.model.Cuisine;
import br.dev.s2w.jfoods.api.domain.repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CuisineRegisterService {

    @Autowired
    private CuisineRepository cuisineRepository;

    public Cuisine save(Cuisine cuisine) {
        return cuisineRepository.save(cuisine);
    }

    public void remove(Long cuisineId) {
        try {
            cuisineRepository.remove(cuisineId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("There is no cuisine registration with the code %d", cuisineId));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("The cuisine with code %d cannot be removed because it is in use", cuisineId));
        }
    }

}
