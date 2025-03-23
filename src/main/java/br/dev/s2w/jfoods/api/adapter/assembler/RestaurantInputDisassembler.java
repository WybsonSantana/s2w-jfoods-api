package br.dev.s2w.jfoods.api.adapter.assembler;

import br.dev.s2w.jfoods.api.adapter.model.input.RestaurantInput;
import br.dev.s2w.jfoods.api.domain.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurant toDomainObject(RestaurantInput restaurantInput) {
        return modelMapper.map(restaurantInput, Restaurant.class);
    }

}
