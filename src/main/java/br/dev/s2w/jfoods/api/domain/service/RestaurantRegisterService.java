package br.dev.s2w.jfoods.api.domain.service;

import br.dev.s2w.jfoods.api.domain.exception.EntityNotFoundException;
import br.dev.s2w.jfoods.api.domain.model.Cuisine;
import br.dev.s2w.jfoods.api.domain.model.Restaurant;
import br.dev.s2w.jfoods.api.domain.repository.CuisineRepository;
import br.dev.s2w.jfoods.api.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantRegisterService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CuisineRepository cuisineRepository;

    public Restaurant save(Restaurant restaurant) {
        Long cuisineId = restaurant.getCuisine().getId();
        Cuisine currentCuisine = cuisineRepository.search(cuisineId);

        if (currentCuisine == null) {
            throw new EntityNotFoundException(String.format("There is no cuisine registration with the code %d", cuisineId));
        }

        restaurant.setCuisine(currentCuisine);

        return restaurantRepository.save(restaurant);
    }

}
