package br.dev.s2w.jfoods.api.core.jackson;

import br.dev.s2w.jfoods.api.adapter.model.mixin.CityMixin;
import br.dev.s2w.jfoods.api.adapter.model.mixin.CuisineMixin;
import br.dev.s2w.jfoods.api.adapter.model.mixin.RestaurantMixin;
import br.dev.s2w.jfoods.api.domain.model.City;
import br.dev.s2w.jfoods.api.domain.model.Cuisine;
import br.dev.s2w.jfoods.api.domain.model.Restaurant;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public JacksonMixinModule() {
        setMixInAnnotation(City.class, CityMixin.class);
        setMixInAnnotation(Cuisine.class, CuisineMixin.class);
        setMixInAnnotation(Restaurant.class, RestaurantMixin.class);
    }

}
