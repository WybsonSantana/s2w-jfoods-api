package br.dev.s2w.jfoods.api.adapter.assembler;

import br.dev.s2w.jfoods.api.adapter.model.CuisineModel;
import br.dev.s2w.jfoods.api.adapter.model.RestaurantModel;
import br.dev.s2w.jfoods.api.domain.model.Restaurant;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantModelAssembler {

    public RestaurantModel toModel(Restaurant restaurant) {
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

    public List<RestaurantModel> toCollectionModel(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
