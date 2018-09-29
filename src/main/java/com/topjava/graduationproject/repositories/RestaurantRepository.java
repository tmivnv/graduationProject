package com.topjava.graduationproject.repositories;

import com.topjava.graduationproject.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
