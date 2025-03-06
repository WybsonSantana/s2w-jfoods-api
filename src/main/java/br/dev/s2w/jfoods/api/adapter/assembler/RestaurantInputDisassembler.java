package br.dev.s2w.jfoods.api.adapter.assembler;

import br.dev.s2w.jfoods.api.adapter.model.input.RestaurantInput;
import br.dev.s2w.jfoods.api.domain.model.Cuisine;
import br.dev.s2w.jfoods.api.domain.model.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantInputDisassembler {

    public Restaurant toDomainObject(RestaurantInput restaurantInput) {
        Cuisine cuisine = new Cuisine();
        cuisine.setId(restaurantInput.getCuisine().getId());

        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantInput.getName());
        restaurant.setDeliveryFee(restaurantInput.getDeliveryFee());
        restaurant.setCuisine(cuisine);

        return restaurant;
    }

}
