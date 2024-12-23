package br.dev.s2w.jfoods.api.domain.service;

import br.dev.s2w.jfoods.api.domain.model.Cuisine;
import br.dev.s2w.jfoods.api.domain.repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuisineRegisterService {

    @Autowired
    private CuisineRepository cuisineRepository;

    public Cuisine save(Cuisine cuisine) {
        return cuisineRepository.save(cuisine);
    }
}