package br.dev.s2w.jfoods.api.adapter.controller;

import br.dev.s2w.jfoods.api.domain.exception.EntityNotFoundException;
import br.dev.s2w.jfoods.api.domain.model.Restaurant;
import br.dev.s2w.jfoods.api.domain.repository.RestaurantRepository;
import br.dev.s2w.jfoods.api.domain.service.RestaurantRegisterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantRegisterService restaurantRegister;

    @GetMapping
    public List<Restaurant> list() {
        return restaurantRepository.findAll();
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> search(@PathVariable Long restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);

        if (restaurant.isPresent()) {
            return ResponseEntity.ok(restaurant.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Restaurant restaurant) {
        try {
            restaurant = restaurantRegister.save(restaurant);

            return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<?> update(@PathVariable Long restaurantId, @RequestBody Restaurant restaurant) {
        try {
            Optional<Restaurant> currentRestaurant = restaurantRepository.findById(restaurantId);

            if (currentRestaurant.isPresent()) {
                BeanUtils.copyProperties(restaurant, currentRestaurant.get(), "id");

                Restaurant savedRestaurant = restaurantRegister.save(currentRestaurant.get());

                return ResponseEntity.ok(savedRestaurant);
            }

            return ResponseEntity.notFound().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{restaurantId}")
    public ResponseEntity<?> patch(@PathVariable Long restaurantId, @RequestBody Map<String, Object> fields) {
        Optional<Restaurant> currentRestaurant = restaurantRepository.findById(restaurantId);

        if (currentRestaurant.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        merge(fields, currentRestaurant.get());

        return update(restaurantId, currentRestaurant.get());
    }

    private void merge(Map<String, Object> fields, Restaurant targetRestaurant) {
        ObjectMapper mapper = new ObjectMapper();
        Restaurant currentRestaurant = mapper.convertValue(fields, Restaurant.class);

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Restaurant.class, key);
            Objects.requireNonNull(field).setAccessible(true);

            Object newValue = ReflectionUtils.getField(field, currentRestaurant);

            ReflectionUtils.setField(field, targetRestaurant, newValue);
        });
    }

}
