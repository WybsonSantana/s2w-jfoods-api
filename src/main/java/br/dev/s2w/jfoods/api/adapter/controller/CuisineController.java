package br.dev.s2w.jfoods.api.adapter.controller;

import br.dev.s2w.jfoods.api.domain.exception.EntityInUseException;
import br.dev.s2w.jfoods.api.domain.exception.EntityNotFoundException;
import br.dev.s2w.jfoods.api.domain.model.Cuisine;
import br.dev.s2w.jfoods.api.domain.repository.CuisineRepository;
import br.dev.s2w.jfoods.api.domain.service.CuisineRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cuisines")
public class CuisineController {

    @Autowired
    private CuisineRepository cuisineRepository;

    @Autowired
    private CuisineRegisterService cuisineRegister;

    @GetMapping
    public List<Cuisine> list() {
        return cuisineRepository.list();
    }

    @GetMapping("/{cuisineId}")
    public ResponseEntity<Cuisine> search(@PathVariable Long cuisineId) {
        Cuisine cuisine = cuisineRepository.search(cuisineId);

        if (cuisine != null) {
            return ResponseEntity.ok(cuisine);
        }

        //return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cuisine add(@RequestBody Cuisine cuisine) {
        return cuisineRegister.save(cuisine);
    }

    @PutMapping("/{cuisineId}")
    public ResponseEntity<Cuisine> update(@PathVariable Long cuisineId, @RequestBody Cuisine cuisine) {
        Cuisine currentCuisine = cuisineRepository.search(cuisineId);

        if (currentCuisine != null) {
            BeanUtils.copyProperties(cuisine, currentCuisine, "id");

            currentCuisine = cuisineRegister.save(currentCuisine);
            return ResponseEntity.ok(currentCuisine);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cuisineId}")
    public ResponseEntity<Cuisine> remove(@PathVariable Long cuisineId) {
        try {
            cuisineRegister.remove(cuisineId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
