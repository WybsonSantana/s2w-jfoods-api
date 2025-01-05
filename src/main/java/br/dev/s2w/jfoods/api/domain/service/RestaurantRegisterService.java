package br.dev.s2w.jfoods.api.domain.service;

import br.dev.s2w.jfoods.api.domain.exception.EntityNotFoundException;
import br.dev.s2w.jfoods.api.domain.model.Cuisine;
import br.dev.s2w.jfoods.api.domain.model.Restaurant;
import br.dev.s2w.jfoods.api.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantRegisterService {

    private static final String RESTAURANT_NOT_FOUND_MESSAGE = "There is no restaurant registration with the code %d";

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CuisineRegisterService cuisineRegister;

    public Restaurant find(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(RESTAURANT_NOT_FOUND_MESSAGE, restaurantId)));
    }

    public Restaurant save(Restaurant restaurant) {
        Long cuisineId = restaurant.getCuisine().getId();
        Cuisine currentCuisine = cuisineRegister.find(cuisineId);

        restaurant.setCuisine(currentCuisine);

        return restaurantRepository.save(restaurant);
    }

}
