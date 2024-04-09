package com.vladtam.springboot.repos;

import com.vladtam.springboot.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepo extends CrudRepository<Address, Long> {
}
