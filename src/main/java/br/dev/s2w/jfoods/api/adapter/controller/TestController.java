package br.dev.s2w.jfoods.api.adapter.controller;

import br.dev.s2w.jfoods.api.domain.model.Cuisine;
import br.dev.s2w.jfoods.api.domain.model.Restaurant;
import br.dev.s2w.jfoods.api.domain.repository.CuisineRepository;
import br.dev.s2w.jfoods.api.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CuisineRepository cuisineRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/cuisines/by-name")
    public List<Cuisine> cuisinesByName(@RequestParam String name) {
        return cuisineRepository.findAllByNameContaining(name);
    }

    @GetMapping("/cuisines/unique-by-name")
    public Optional<Cuisine> cuisineByName(@RequestParam String name) {
        return cuisineRepository.findByName(name);
    }

    @GetMapping("/restaurants/by-delivery-fee")
    public List<Restaurant> restaurantsByDeliveryFee(@RequestParam BigDecimal initialFee, @RequestParam BigDecimal finalFee) {
        return restaurantRepository.findByDeliveryFeeBetween(initialFee, finalFee);
    }

    @GetMapping("/restaurants/by-name")
    public List<Restaurant> restaurantsByName(@RequestParam String name, @RequestParam Long cuisineId) {
        return restaurantRepository.findByNameContainingAndCuisineId(name, cuisineId);
    }

}
