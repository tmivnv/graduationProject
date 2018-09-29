package com.topjava.graduationproject.service;

import com.topjava.graduationproject.entities.Dish;
import com.topjava.graduationproject.entities.Restaurant;

import java.util.List;
import java.util.Set;

public interface RestaurantService {
    List<Restaurant> getAllRestaurants();
    Restaurant getRestaurantById(Long id);
    Restaurant updateMenu(Long id, Set<Dish> menu);
}
