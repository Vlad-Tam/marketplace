package com.vladtam.springboot.repos;

import com.vladtam.springboot.entities.Wish;
import com.vladtam.springboot.entities.pk.WishPK;
import org.springframework.data.repository.CrudRepository;

public interface WishRepo extends CrudRepository<Wish, WishPK> {
}
