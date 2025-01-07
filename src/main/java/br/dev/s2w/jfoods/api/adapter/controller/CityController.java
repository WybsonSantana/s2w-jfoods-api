package br.dev.s2w.jfoods.api.adapter.controller;

import br.dev.s2w.jfoods.api.domain.exception.BusinessException;
import br.dev.s2w.jfoods.api.domain.exception.StateNotFoundException;
import br.dev.s2w.jfoods.api.domain.model.City;
import br.dev.s2w.jfoods.api.domain.repository.CityRepository;
import br.dev.s2w.jfoods.api.domain.service.CityRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return cityRepository.findAll();
    }

    @GetMapping("/{cityId}")
    public City find(@PathVariable Long cityId) {
        return cityRegister.find(cityId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public City add(@RequestBody City city) {
        try {
            return cityRegister.save(city);
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cityId}")
    public City update(@PathVariable Long cityId, @RequestBody City city) {
        try {
            City currentCity = cityRegister.find(cityId);

            BeanUtils.copyProperties(city, currentCity, "id");

            return cityRegister.save(currentCity);
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{cityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long cityId) {
        cityRegister.remove(cityId);
    }

}
