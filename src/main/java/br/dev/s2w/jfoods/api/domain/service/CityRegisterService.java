package br.dev.s2w.jfoods.api.domain.service;

import br.dev.s2w.jfoods.api.domain.exception.EntityInUseException;
import br.dev.s2w.jfoods.api.domain.exception.EntityNotFoundException;
import br.dev.s2w.jfoods.api.domain.model.City;
import br.dev.s2w.jfoods.api.domain.model.State;
import br.dev.s2w.jfoods.api.domain.repository.CityRepository;
import br.dev.s2w.jfoods.api.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CityRegisterService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    public City save(City city) {
        Long stateId = city.getState().getId();
        State currentState = stateRepository.search(stateId);

        if (currentState == null) {
            throw new EntityNotFoundException(String.format("There is no state registration with the code %d", stateId));
        }

        city.setState(currentState);

        return cityRepository.save(city);
    }

    public void remove(Long cityId) {
        try {
            cityRepository.remove(cityId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("There is no city registration with the code %d", cityId));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("The city with code %d cannot be removed because it is in use", cityId));
        }
    }

}
