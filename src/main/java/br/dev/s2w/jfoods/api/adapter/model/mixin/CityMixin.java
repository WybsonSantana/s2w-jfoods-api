package br.dev.s2w.jfoods.api.adapter.model.mixin;

import br.dev.s2w.jfoods.api.domain.model.State;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public abstract class CityMixin {

    @JsonIgnoreProperties(value = "name", allowGetters = true)
    private State state;

}
