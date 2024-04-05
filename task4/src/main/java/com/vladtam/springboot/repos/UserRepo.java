package com.vladtam.springboot.repos;

import com.vladtam.springboot.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
}
