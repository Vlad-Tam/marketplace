package com.vladtam.springboot.repos;

import com.vladtam.springboot.entities.City;
import org.springframework.data.repository.CrudRepository;

public interface CityRepo extends CrudRepository<City, Long> {
}
