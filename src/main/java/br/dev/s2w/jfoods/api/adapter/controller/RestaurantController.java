package br.dev.s2w.jfoods.api.adapter.controller;

import br.dev.s2w.jfoods.api.adapter.model.CuisineModel;
import br.dev.s2w.jfoods.api.adapter.model.RestaurantModel;
import br.dev.s2w.jfoods.api.adapter.model.input.RestaurantInput;
import br.dev.s2w.jfoods.api.domain.exception.BusinessException;
import br.dev.s2w.jfoods.api.domain.exception.CuisineNotFoundException;
import br.dev.s2w.jfoods.api.domain.model.Cuisine;
import br.dev.s2w.jfoods.api.domain.model.Restaurant;
import br.dev.s2w.jfoods.api.domain.repository.RestaurantRepository;
import br.dev.s2w.jfoods.api.domain.service.RestaurantRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantRegisterService restaurantRegister;

    @GetMapping
    public List<RestaurantModel> list() {
        return toCollectionModel(restaurantRepository.findAll());
    }

    @GetMapping("/{restaurantId}")
    public RestaurantModel find(@PathVariable Long restaurantId) {
        return toModel(restaurantRegister.find(restaurantId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantModel add(@RequestBody @Valid RestaurantInput restaurantInput) {
        try {
            Restaurant restaurant = toDomainObject(restaurantInput);

            return toModel(restaurantRegister.save(restaurant));
        } catch (CuisineNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PutMapping("/{restaurantId}")
    public RestaurantModel update(@PathVariable Long restaurantId, @RequestBody @Valid RestaurantInput restaurantInput) {
        try {
            Restaurant restaurant = toDomainObject(restaurantInput);

            Restaurant currentRestaurant = restaurantRegister.find(restaurantId);

            BeanUtils.copyProperties(restaurant, currentRestaurant,
                    "id", "paymentMethods", "address", "registrationDate", "products");

            return toModel(restaurantRegister.save(currentRestaurant));
        } catch (CuisineNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    private RestaurantModel toModel(Restaurant restaurant) {
        CuisineModel cuisineModel = new CuisineModel();
        cuisineModel.setId(restaurant.getCuisine().getId());
        cuisineModel.setName(restaurant.getCuisine().getName());

        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setId(restaurant.getId());
        restaurantModel.setName(restaurant.getName());
        restaurantModel.setDeliveryFee(restaurant.getDeliveryFee());
        restaurantModel.setCuisine(cuisineModel);

        return restaurantModel;
    }

    private List<RestaurantModel> toCollectionModel(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    private Restaurant toDomainObject(RestaurantInput restaurantInput) {
        Cuisine cuisine = new Cuisine();
        cuisine.setId(restaurantInput.getCuisine().getId());

        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantInput.getName());
        restaurant.setDeliveryFee(restaurantInput.getDeliveryFee());
        restaurant.setCuisine(cuisine);

        return restaurant;
    }

}
