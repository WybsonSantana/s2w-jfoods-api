package br.dev.s2w.jfoods.api.adapter.controller;

import br.dev.s2w.jfoods.api.domain.model.State;
import br.dev.s2w.jfoods.api.domain.repository.StateRepository;
import br.dev.s2w.jfoods.api.domain.service.StateRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return stateRepository.findAll();
    }

    @GetMapping("/{stateId}")
    public State find(@PathVariable Long stateId) {
        return stateRegister.find(stateId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public State add(@RequestBody State state) {
        return stateRegister.save(state);
    }

    @PutMapping("/{stateId}")
    public State update(@PathVariable Long stateId, @RequestBody State state) {
        State currentState = stateRegister.find(stateId);

        BeanUtils.copyProperties(state, currentState, "id");

        return stateRegister.save(currentState);
    }

    @DeleteMapping("/{stateId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long stateId) {
        stateRegister.remove(stateId);
    }

}
