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
import java.util.Optional;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityRegisterService cityRegister;

    @GetMapping
    public List<City> list() {
        return cityRepository.findAll();
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<City> search(@PathVariable Long cityId) {
        Optional<City> city = cityRepository.findById(cityId);

        if (city.isPresent()) {
            return ResponseEntity.ok(city.get());
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
            Optional<City> currentCity = cityRepository.findById(cityId);

            if (currentCity.isPresent()) {
                BeanUtils.copyProperties(city, currentCity.get(), "id");

                City savedCity = cityRegister.save(currentCity.get());
                return ResponseEntity.ok(savedCity);
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
