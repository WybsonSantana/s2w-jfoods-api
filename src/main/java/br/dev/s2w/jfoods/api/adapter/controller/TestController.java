package br.dev.s2w.jfoods.api.adapter.controller;

import br.dev.s2w.jfoods.api.domain.model.Cuisine;
import br.dev.s2w.jfoods.api.domain.repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CuisineRepository cuisineRepository;

    @GetMapping("/cuisines/by-name")
    public List<Cuisine> cuisinesByName(@RequestParam String name) {
        return cuisineRepository.findAllByName(name);
    }

    @GetMapping("/cuisines/unique-by-name")
    public Optional<Cuisine> cuisineByName(@RequestParam String name) {
        return cuisineRepository.findByName(name);
    }
}
