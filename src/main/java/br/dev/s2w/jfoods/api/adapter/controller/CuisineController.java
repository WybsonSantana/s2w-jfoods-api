package br.dev.s2w.jfoods.api.adapter.controller;

import br.dev.s2w.jfoods.api.domain.model.Cuisine;
import br.dev.s2w.jfoods.api.domain.repository.CuisineRepository;
import br.dev.s2w.jfoods.api.domain.service.CuisineRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        return cuisineRepository.findAll();
    }

    @GetMapping("/{cuisineId}")
    public Cuisine find(@PathVariable Long cuisineId) {
        return cuisineRegister.find(cuisineId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cuisine add(@RequestBody @Valid Cuisine cuisine) {
        return cuisineRegister.save(cuisine);
    }

    @PutMapping("/{cuisineId}")
    public Cuisine update(@PathVariable Long cuisineId, @RequestBody Cuisine cuisine) {
        Cuisine currentCuisine = cuisineRegister.find(cuisineId);

        BeanUtils.copyProperties(cuisine, currentCuisine, "id");

        return cuisineRegister.save(currentCuisine);
    }

    @DeleteMapping("/{cuisineId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long cuisineId) {
        cuisineRegister.remove(cuisineId);
    }

}
