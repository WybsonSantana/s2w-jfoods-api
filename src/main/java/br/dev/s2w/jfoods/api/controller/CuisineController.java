package br.dev.s2w.jfoods.api.controller;

import br.dev.s2w.jfoods.api.domain.model.Cuisine;
import br.dev.s2w.jfoods.api.domain.repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cuisines")
public class CuisineController {

    @Autowired
    private CuisineRepository cuisineRepository;

    @GetMapping
    public List<Cuisine> list() {
        return cuisineRepository.list();
    }

    @GetMapping("/{cuisineId}")
    public Cuisine search(@PathVariable Long cuisineId) {
        return cuisineRepository.search(cuisineId);
    }
}
