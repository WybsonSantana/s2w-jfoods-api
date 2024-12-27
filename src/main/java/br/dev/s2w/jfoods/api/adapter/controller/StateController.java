package br.dev.s2w.jfoods.api.adapter.controller;

import br.dev.s2w.jfoods.api.domain.exception.EntityInUseException;
import br.dev.s2w.jfoods.api.domain.exception.EntityNotFoundException;
import br.dev.s2w.jfoods.api.domain.model.State;
import br.dev.s2w.jfoods.api.domain.repository.StateRepository;
import br.dev.s2w.jfoods.api.domain.service.StateRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateRegisterService stateRegister;

    @GetMapping
    public List<State> list() {
        return stateRepository.list();
    }

    @GetMapping("/{stateId}")
    public ResponseEntity<State> search(@PathVariable Long stateId) {
        State state = stateRepository.search(stateId);

        if (state != null) {
            return ResponseEntity.ok(state);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public State add(@RequestBody State state) {
        return stateRegister.save(state);
    }

    @PutMapping("/{stateId}")
    public ResponseEntity<State> update(@PathVariable Long stateId, @RequestBody State state) {
        State currentState = stateRepository.search(stateId);

        if (currentState != null) {
            BeanUtils.copyProperties(state, currentState, "id");

            currentState = stateRegister.save(currentState);
            return ResponseEntity.ok(currentState);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{stateId}")
    public ResponseEntity<?> remove(@PathVariable Long stateId) {
        try {
            stateRegister.remove(stateId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
