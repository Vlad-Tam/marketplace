package com.vladtam.springboot.repos;

import com.vladtam.springboot.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<Category, Long> {
}
