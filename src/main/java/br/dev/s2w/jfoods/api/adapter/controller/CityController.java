package br.dev.s2w.jfoods.api.adapter.controller;

import br.dev.s2w.jfoods.api.domain.exception.EntityInUseException;
import br.dev.s2w.jfoods.api.domain.exception.EntityNotFoundException;
import br.dev.s2w.jfoods.api.domain.model.City;
import br.dev.s2w.jfoods.api.domain.repository.CityRepository;
import br.dev.s2w.jfoods.api.domain.service.CityRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityRegisterService cityRegister;

    @GetMapping
    public List<City> list() {
        return cityRepository.list();
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<City> search(@PathVariable Long cityId) {
        City city = cityRepository.search(cityId);

        if (city != null) {
            return ResponseEntity.ok(city);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody City city) {
        try {
            city = cityRegister.save(city);
            return ResponseEntity.status(HttpStatus.CREATED).body(city);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{cityId}")
    public ResponseEntity<?> update(@PathVariable Long cityId, @RequestBody City city) {
        try {
            City currentCity = cityRepository.search(cityId);

            if (currentCity != null) {
                BeanUtils.copyProperties(city, currentCity, "id");

                currentCity = cityRegister.save(currentCity);

                return ResponseEntity.ok(currentCity);
            }

            return ResponseEntity.notFound().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{cityId}")
    public ResponseEntity<City> remove(@PathVariable Long cityId) {
        try {
            cityRegister.remove(cityId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
