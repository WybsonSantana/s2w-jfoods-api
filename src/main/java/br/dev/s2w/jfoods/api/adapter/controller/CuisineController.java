package br.dev.s2w.jfoods.api.adapter.controller;

import br.dev.s2w.jfoods.api.adapter.model.CuisinesXmlWrapper;
import br.dev.s2w.jfoods.api.domain.model.Cuisine;
import br.dev.s2w.jfoods.api.domain.repository.CuisineRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cuisines")
public class CuisineController {

    @Autowired
    private CuisineRepository cuisineRepository;

    @GetMapping
    public List<Cuisine> listJson() {
        return cuisineRepository.list();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CuisinesXmlWrapper listXml() {
        return new CuisinesXmlWrapper((cuisineRepository.list()));
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
        return cuisineRepository.save(cuisine);
    }

    @PutMapping("/{cuisineId}")
    public ResponseEntity<Cuisine> update(@PathVariable Long cuisineId, @RequestBody Cuisine cuisine) {
        Cuisine currentCuisine = cuisineRepository.search(cuisineId);

        if (currentCuisine != null) {
            //currentCuisine.setName(cuisine.getName());

            BeanUtils.copyProperties(cuisine, currentCuisine, "id");

            currentCuisine = cuisineRepository.save(currentCuisine);
            return ResponseEntity.ok(currentCuisine);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cuisineId}")
    public ResponseEntity<Cuisine> remove(@PathVariable Long cuisineId) {
        try {
            Cuisine cuisine = cuisineRepository.search(cuisineId);

            if (cuisine != null) {
                cuisineRepository.remove(cuisine);
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
