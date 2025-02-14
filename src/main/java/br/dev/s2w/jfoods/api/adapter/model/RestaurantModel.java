package br.dev.s2w.jfoods.api.adapter.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantModel {

    private Long id;

    private String name;

    private BigDecimal deliveryFee;

    private CuisineModel cuisine;

}
