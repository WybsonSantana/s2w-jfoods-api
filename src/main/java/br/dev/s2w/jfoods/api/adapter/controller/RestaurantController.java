package br.dev.s2w.jfoods.api.adapter.controller;

import br.dev.s2w.jfoods.api.adapter.assembler.RestaurantInputDisassembler;
import br.dev.s2w.jfoods.api.adapter.assembler.RestaurantModelAssembler;
import br.dev.s2w.jfoods.api.adapter.model.RestaurantModel;
import br.dev.s2w.jfoods.api.adapter.model.input.RestaurantInput;
import br.dev.s2w.jfoods.api.domain.exception.BusinessException;
import br.dev.s2w.jfoods.api.domain.exception.CuisineNotFoundException;
import br.dev.s2w.jfoods.api.domain.model.Restaurant;
import br.dev.s2w.jfoods.api.domain.repository.RestaurantRepository;
import br.dev.s2w.jfoods.api.domain.service.RestaurantRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantRegisterService restaurantRegister;

    @Autowired
    private RestaurantModelAssembler restaurantModelAssembler;

    @Autowired
    private RestaurantInputDisassembler restaurantInputDisassembler;

    @GetMapping
    public List<RestaurantModel> list() {
        return restaurantModelAssembler.toCollectionModel(restaurantRepository.findAll());
    }

    @GetMapping("/{restaurantId}")
    public RestaurantModel find(@PathVariable Long restaurantId) {
        return restaurantModelAssembler.toModel(restaurantRegister.find(restaurantId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantModel add(@RequestBody @Valid RestaurantInput restaurantInput) {
        try {
            Restaurant restaurant = restaurantInputDisassembler.toDomainObject(restaurantInput);

            return restaurantModelAssembler.toModel(restaurantRegister.save(restaurant));
        } catch (CuisineNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PutMapping("/{restaurantId}")
    public RestaurantModel update(@PathVariable Long restaurantId, @RequestBody @Valid RestaurantInput restaurantInput) {
        try {
            Restaurant restaurant = restaurantInputDisassembler.toDomainObject(restaurantInput);

            Restaurant currentRestaurant = restaurantRegister.find(restaurantId);

            BeanUtils.copyProperties(restaurant, currentRestaurant,
                    "id", "paymentMethods", "address", "registrationDate", "products");

            return restaurantModelAssembler.toModel(restaurantRegister.save(currentRestaurant));
        } catch (CuisineNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

}
