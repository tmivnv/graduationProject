package com.topjava.graduationproject.service;

import com.topjava.graduationproject.entities.Dish;

import java.util.List;

public interface DishService {
    List<Dish> getAllDishes();
    Dish getDishById(Long id);
}
