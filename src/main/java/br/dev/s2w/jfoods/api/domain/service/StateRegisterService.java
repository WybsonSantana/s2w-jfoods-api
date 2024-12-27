package br.dev.s2w.jfoods.api.domain.service;

import br.dev.s2w.jfoods.api.domain.exception.EntityInUseException;
import br.dev.s2w.jfoods.api.domain.exception.EntityNotFoundException;
import br.dev.s2w.jfoods.api.domain.model.State;
import br.dev.s2w.jfoods.api.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class StateRegisterService {

    @Autowired
    private StateRepository stateRepository;

    public State save(State state) {
        return stateRepository.save(state);
    }

    public void remove(Long stateId) {
        try {
            stateRepository.deleteById(stateId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("There is no state registration with the code %d", stateId));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("The state with code %d cannot be removed because it is in use", stateId));
        }
    }

}
