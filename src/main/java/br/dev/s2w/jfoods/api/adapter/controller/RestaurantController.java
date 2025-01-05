package br.dev.s2w.jfoods.api.adapter.controller;

import br.dev.s2w.jfoods.api.domain.exception.BusinessException;
import br.dev.s2w.jfoods.api.domain.exception.EntityNotFoundException;
import br.dev.s2w.jfoods.api.domain.model.Restaurant;
import br.dev.s2w.jfoods.api.domain.repository.RestaurantRepository;
import br.dev.s2w.jfoods.api.domain.service.RestaurantRegisterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    public Restaurant find(@PathVariable Long restaurantId) {
        return restaurantRegister.find(restaurantId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant add(@RequestBody Restaurant restaurant) {
        try {
            return restaurantRegister.save(restaurant);
        } catch (EntityNotFoundException e) {
            throw new BusinessException((e.getMessage()));
        }
    }

    @PutMapping("/{restaurantId}")
    public Restaurant update(@PathVariable Long restaurantId, @RequestBody Restaurant restaurant) {
        Restaurant currentRestaurant = restaurantRegister.find(restaurantId);

        BeanUtils.copyProperties(restaurant, currentRestaurant,
                "id", "paymentMethods", "address", "registrationDate", "products");

        try {
            return restaurantRegister.save(restaurant);
        } catch (EntityNotFoundException e) {
            throw new BusinessException((e.getMessage()));
        }
    }

    @PatchMapping("/{restaurantId}")
    public Restaurant patch(@PathVariable Long restaurantId, @RequestBody Map<String, Object> fields) {
        Restaurant currentRestaurant = restaurantRegister.find(restaurantId);

        merge(fields, currentRestaurant);

        return update(restaurantId, currentRestaurant);
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
