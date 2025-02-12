package br.dev.s2w.jfoods.api.adapter.model.mixin;

import br.dev.s2w.jfoods.api.domain.model.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public abstract class CuisineMixin {

    @JsonIgnore
    private List<Restaurant> restaurants = new ArrayList<>();

}
