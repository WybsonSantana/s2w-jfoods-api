package br.dev.s2w.jfoods.api.adapter.model.mixin;

import br.dev.s2w.jfoods.api.domain.model.Address;
import br.dev.s2w.jfoods.api.domain.model.Cuisine;
import br.dev.s2w.jfoods.api.domain.model.PaymentMethod;
import br.dev.s2w.jfoods.api.domain.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class RestaurantMixin {

    @JsonIgnoreProperties(value = "name", allowGetters = true)
    private Cuisine cuisine;

    @JsonIgnore
    private Address address;

    @JsonIgnore
    private OffsetDateTime registrationDate;

    @JsonIgnore
    private OffsetDateTime lastUpdateDate;

    @JsonIgnore
    private List<PaymentMethod> paymentMethods = new ArrayList<>();

    @JsonIgnore
    private List<Product> products = new ArrayList<>();

}
