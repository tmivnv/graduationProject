package com.topjava.graduationproject.service.impl;

import com.topjava.graduationproject.entities.Dish;
import com.topjava.graduationproject.entities.Restaurant;
import com.topjava.graduationproject.repositories.RestaurantRepository;
import com.topjava.graduationproject.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantRepository repository;



    @Override
    public List<Restaurant> getAllRestaurants() {
        return repository.findAll();
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Restaurant updateMenu(Long id, Set<Dish> menu) {
        Restaurant restaurant = getRestaurantById(id);
        if (restaurant == null) return null;
        restaurant.setMenu(menu);
        repository.saveAndFlush(restaurant);
        return restaurant;
    }



}
