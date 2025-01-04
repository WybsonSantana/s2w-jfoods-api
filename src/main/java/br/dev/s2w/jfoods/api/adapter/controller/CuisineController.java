package br.dev.s2w.jfoods.api.adapter.controller;

import br.dev.s2w.jfoods.api.domain.exception.EntityNotFoundException;
import br.dev.s2w.jfoods.api.domain.model.Cuisine;
import br.dev.s2w.jfoods.api.domain.repository.CuisineRepository;
import br.dev.s2w.jfoods.api.domain.service.CuisineRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cuisines")
public class CuisineController {

    @Autowired
    private CuisineRepository cuisineRepository;

    @Autowired
    private CuisineRegisterService cuisineRegister;

    @GetMapping
    public List<Cuisine> list() {
        return cuisineRepository.findAll();
    }

    @GetMapping("/{cuisineId}")
    public ResponseEntity<Cuisine> search(@PathVariable Long cuisineId) {
        Optional<Cuisine> cuisine = cuisineRepository.findById(cuisineId);

        if (cuisine.isPresent()) {
            return ResponseEntity.ok(cuisine.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cuisine add(@RequestBody Cuisine cuisine) {
        return cuisineRegister.save(cuisine);
    }

    @PutMapping("/{cuisineId}")
    public ResponseEntity<Cuisine> update(@PathVariable Long cuisineId, @RequestBody Cuisine cuisine) {
        Optional<Cuisine> currentCuisine = cuisineRepository.findById(cuisineId);

        if (currentCuisine.isPresent()) {
            BeanUtils.copyProperties(cuisine, currentCuisine.get(), "id");

            Cuisine savedCuisine = cuisineRegister.save(currentCuisine.get());
            return ResponseEntity.ok(savedCuisine);
        }

        return ResponseEntity.notFound().build();
    }

//    @DeleteMapping("/{cuisineId}")
//    public ResponseEntity<?> remove(@PathVariable Long cuisineId) {
//        try {
//            cuisineRegister.remove(cuisineId);
//            return ResponseEntity.noContent().build();
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.notFound().build();
//        } catch (EntityInUseException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//        }
//    }

    @DeleteMapping("/{cuisineId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long cuisineId) {
        try {
            cuisineRegister.remove(cuisineId);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            //throw new ServerWebInputException(e.getMessage());
        }
    }

}
