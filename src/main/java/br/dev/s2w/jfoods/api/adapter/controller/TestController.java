package br.dev.s2w.jfoods.api.adapter.controller;

import br.dev.s2w.jfoods.api.domain.repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CuisineRepository cuisineRepository;

//    @GetMapping("/cuisines/by-name")
//    public List<Cuisine> cuisinesByName(@RequestParam("name") String name) {
//        return cuisineRepository.findByName(name);
//    }
}
