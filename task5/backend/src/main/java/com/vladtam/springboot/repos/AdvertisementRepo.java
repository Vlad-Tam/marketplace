package com.vladtam.springboot.repos;

import com.vladtam.springboot.entities.Advertisement;
import org.springframework.data.repository.CrudRepository;

public interface AdvertisementRepo extends CrudRepository<Advertisement, Long> {
}
