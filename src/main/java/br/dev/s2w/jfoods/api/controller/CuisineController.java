package br.dev.s2w.jfoods.api.controller;

import br.dev.s2w.jfoods.api.domain.model.Cuisine;
import br.dev.s2w.jfoods.api.domain.repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cuisines") //, produces = MediaType.APPLICATION_JSON_VALUE)
public class CuisineController {

    @Autowired
    private CuisineRepository cuisineRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cuisine> listJson() {
        return cuisineRepository.list();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public List<Cuisine> listXml() {
        return cuisineRepository.list();
    }
}
