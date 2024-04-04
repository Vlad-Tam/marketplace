package com.vladtam.springboot.controllers;

import com.vladtam.springboot.entities.City;
import com.vladtam.springboot.repos.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private CityRepo cityRepo;

    @GetMapping
    public String citiesList(Map<String, Object> model){
        Iterable<City> cities = cityRepo.findAll();
        model.put("cities", cities);
        return "cityPages/citiesListPage";
    }

    @GetMapping("/{id}")
    public String cityInfo(Map<String, Object> model, @PathVariable String id){
        Optional<City> cityOptional = cityRepo.findById(Long.parseLong(id));
        if(cityOptional.isPresent()){
            model.put("city", cityOptional.get());
            return "cityPages/cityPage";
        }else
            return "errorPages/errorPage";
    }

    @GetMapping("/new")
    public String newCity(){
        return "cityPages/createCityPage";
    }

    @PostMapping("/creating")
    public String newCity(@RequestParam(name = "name", required = false, defaultValue = "") String name, @RequestParam(name = "region", required = false, defaultValue = "") String region){
        City city = new City(name, region);
        cityRepo.save(city);
        return "redirect:/cities";
    }

    @PostMapping("/{id}/deleting")
    public String deleteCity(@PathVariable String id){
        cityRepo.deleteById(Long.parseLong(id));
        return "redirect:/cities";
    }
}
