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
import java.util.Optional;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateRegisterService stateRegister;

    @GetMapping
    public List<State> list() {
        return stateRepository.findAll();
    }

    @GetMapping("/{stateId}")
    public ResponseEntity<State> search(@PathVariable Long stateId) {
        Optional<State> state = stateRepository.findById(stateId);

        if (state.isPresent()) {
            return ResponseEntity.ok(state.get());
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
        Optional<State> currentState = stateRepository.findById(stateId);

        if (currentState.isPresent()) {
            BeanUtils.copyProperties(state, currentState.get(), "id");

            State savedState = stateRegister.save(currentState.get());
            return ResponseEntity.ok(savedState);
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
