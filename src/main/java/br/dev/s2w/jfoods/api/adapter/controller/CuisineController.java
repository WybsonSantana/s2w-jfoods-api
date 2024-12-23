package br.dev.s2w.jfoods.api.adapter.controller;

import br.dev.s2w.jfoods.api.adapter.model.CuisinesXmlWrapper;
import br.dev.s2w.jfoods.api.domain.model.Cuisine;
import br.dev.s2w.jfoods.api.domain.repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{cuisineId}")
    public ResponseEntity<Cuisine> search(@PathVariable Long cuisineId) {
        Cuisine cuisine = cuisineRepository.search(cuisineId);

        //return ResponseEntity.status(HttpStatus.OK).body(cuisine);
        //return ResponseEntity.ok(cuisine);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "http://api.jfoods.local:8080/cuisines");

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .headers(headers)
                .build();
    }
}
