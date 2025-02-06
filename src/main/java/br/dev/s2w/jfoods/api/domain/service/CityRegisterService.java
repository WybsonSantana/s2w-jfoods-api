package br.dev.s2w.jfoods.api.domain.service;

import br.dev.s2w.jfoods.api.domain.exception.CityNotFoundException;
import br.dev.s2w.jfoods.api.domain.exception.EntityInUseException;
import br.dev.s2w.jfoods.api.domain.model.City;
import br.dev.s2w.jfoods.api.domain.model.State;
import br.dev.s2w.jfoods.api.domain.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityRegisterService {

    private static final String CITY_IN_USE_MESSAGE = "The city with code %d cannot be removed because it is in use";

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRegisterService stateRegister;

    public City find(Long cityId) {
        return cityRepository.findById(cityId)
                .orElseThrow(() -> new CityNotFoundException(cityId));
    }

    @Transactional
    public City save(City city) {
        Long stateId = city.getState().getId();
        State currentState = stateRegister.find(stateId);

        city.setState(currentState);

        return cityRepository.save(city);
    }

    @Transactional
    public void remove(Long cityId) {
        try {
            cityRepository.deleteById(cityId);
        } catch (EmptyResultDataAccessException e) {
            throw new CityNotFoundException(cityId);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(CITY_IN_USE_MESSAGE, cityId));
        }
    }

}
