package br.dev.s2w.jfoods.api.adapter.controller;

import br.dev.s2w.jfoods.api.adapter.model.CuisinesXmlWrapper;
import br.dev.s2w.jfoods.api.domain.model.Cuisine;
import br.dev.s2w.jfoods.api.domain.repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cuisines")
public class CuisineController {

    @Autowired
    private CuisineRepository cuisineRepository;

    @GetMapping
    public List<Cuisine> listJson() {
        return cuisineRepository.list();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CuisinesXmlWrapper listXml() {
        return new CuisinesXmlWrapper((cuisineRepository.list()));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{cuisineId}")
    public Cuisine search(@PathVariable Long cuisineId) {
        return cuisineRepository.search(cuisineId);
    }
}
