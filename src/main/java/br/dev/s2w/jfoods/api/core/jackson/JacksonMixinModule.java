package br.dev.s2w.jfoods.api.core.jackson;

import br.dev.s2w.jfoods.api.adapter.model.mixin.RestaurantMixin;
import br.dev.s2w.jfoods.api.domain.model.Restaurant;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public JacksonMixinModule() {
        setMixInAnnotation(Restaurant.class, RestaurantMixin.class);
    }

}
